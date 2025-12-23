package ru.system.thundercloud.engine.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.system.thundercloud.engine.db.ThunderCloudDataBaseEngine;
import ru.system.thundercloud.engine.db.dto.ProcessExecutionTask;
import ru.system.thundercloud.engine.db.tables.TCLExecution;
import ru.system.thundercloud.engine.db.tables.TCLVariable;
import ru.system.thundercloud.engine.exceptions.ProcessNotFoundException;
import ru.system.thundercloud.engine.exceptions.TCLVariablesErrorException;
import ru.system.thundercloud.engine.service.process.ThunderCloudDelegate;
import ru.system.thundercloud.engine.service.process.ThunderCloudGetaway;
import ru.system.thundercloud.engine.service.process.ThunderCloudProcess;
import ru.system.thundercloud.engine.service.process.ThunderCloudTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static ru.system.thundercloud.engine.util.Constants.THUNDER_CLOUD_END_GETAWAY;

/**
 *
 * @author DRakovskiy
 */
@Service
public class ThunderCloudEngine {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ThunderCloudEngine.class);
    private static final String VERSION = "0.0.1";

    private final ThunderCloudDataBaseEngine thunderCloudDataBaseEngine;

    private final Map<String, ThunderCloudProcess> processMap;

    public ThunderCloudEngine(List<ThunderCloudProcess> processes,
                              ThunderCloudDataBaseEngine thunderCloudDataBaseEngine
    ) {
        this.thunderCloudDataBaseEngine = thunderCloudDataBaseEngine;
        processMap = new HashMap<>();
        for (ThunderCloudProcess process : processes) {
            processMap.put(process.getName(), process);
        }
    }

    @PostConstruct
    public void init() {
        log.info("init...");
        log.info("version: {}", VERSION);
        onMigrationComplete();
        dropEndedProcess();
    }

    public void onMigrationComplete() {
        log.debug("Проверка заполнения таблицы процессов");
        thunderCloudDataBaseEngine.checkProcessesInDatabase(processMap);
    }

    public void dropEndedProcess() {
        thunderCloudDataBaseEngine.deleteCompletedTasksAndExecutions();
    }

    @Transactional(readOnly = true)
    public String executionTask(String executionId, String taskName) {

        TCLExecution tclExecution = thunderCloudDataBaseEngine.executionById(executionId);

        ProcessExecutionTask processExecutionTask = thunderCloudDataBaseEngine.getProcessExecutionTaskByExecutionId(executionId);

        ThunderCloudProcess thunderCloudProcess = processMap.get(processExecutionTask.processName());

        ThunderCloudGetaway getaway = thunderCloudProcess
                .getExecution()
                .getGetaways()
                .get(processExecutionTask.taskName());

        if (Objects.isNull(getaway)) throw new ProcessNotFoundException("Не найден процесс с id= "
                + executionId
                + " и точкой продолжения имеющей задание- " + taskName
        );

        ThunderCloudTask task = getaway
                .getTasks()
                .get(taskName);

        if (Objects.isNull(task)) throw new ProcessNotFoundException("Не найден процесс с id= "
                + executionId
                + " и заданием- " + taskName
        );

        List<ThunderCloudDelegate> delegates = task.getDelegates();

        Map<String, Object> tclVariablesMap;

        if (Objects.nonNull(delegates) && !delegates.isEmpty()) {
            tclVariablesMap = thunderCloudDataBaseEngine.getTCLVariablesForThisExecution(executionId);
        } else {
            tclVariablesMap = new HashMap<>();
        }

        delegates.forEach(delegate -> {
            delegate.execute(tclVariablesMap);
        });

        thunderCloudDataBaseEngine.setNewGetawayForTask(executionId, task.getNextGetaway(), isEndGetawayOnNext(task.getNextGetaway()));

        List<TCLVariable> tclVariables = new ArrayList<>(tclVariablesMap.size());

        tclVariablesMap.forEach((k, v) -> {
            TCLVariable tclVariable = new TCLVariable();
            tclVariable.setId(executionId + "_" + k);
            tclVariable.setKey(k);
            tclVariable.setDeserializedValue(v);
            tclVariable.setExecutionId(tclExecution.id());
            tclVariables.add(tclVariable);
        });

        try {
            thunderCloudDataBaseEngine.saveTCLVariableForThisProcessInNewTransaction(tclVariables);
        } catch (Exception e) {
            throw new TCLVariablesErrorException(e.getMessage());
        }

        return executionId;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String startNewExecutionForProcess(String processName, Map<String, Object> variables) {

        ThunderCloudProcess process = getProcessIfIsPresentOrError(processName);

        TCLExecution tclExecution = thunderCloudDataBaseEngine.createExecution(process);

        List<TCLVariable> tclVariables = new ArrayList<>(variables.size());

        variables.forEach((k, v) -> {
            TCLVariable tclVariable = new TCLVariable();
            tclVariable.setId(tclExecution.id() + "_" + k);
            tclVariable.setKey(k);
            tclVariable.setDeserializedValue(v);
            tclVariable.setExecutionId(tclExecution.id());
            tclVariables.add(tclVariable);
        });

        try {
            thunderCloudDataBaseEngine.saveTCLVariableForThisProcess(tclVariables);
        } catch (Exception e) {
            throw new TCLVariablesErrorException(e.getMessage());
        }

        return tclExecution.id();

    }

    private Boolean isEndGetawayOnNext(String getaway) {
        return THUNDER_CLOUD_END_GETAWAY.equalsIgnoreCase(getaway);
    }

    private ThunderCloudProcess getProcessIfIsPresentOrError(String processName) {

        ThunderCloudProcess process = processMap.get(processName);

        if (Objects.isNull(process)) {
            String errorMessage = "Процесс с таким названием не найден: " + processName;
            log.error(errorMessage);
            throw new ProcessNotFoundException(errorMessage);
        }

        return process;
    }


}
