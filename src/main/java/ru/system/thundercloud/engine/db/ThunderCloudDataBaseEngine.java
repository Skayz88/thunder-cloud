package ru.system.thundercloud.engine.db;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.system.thundercloud.engine.db.dto.ProcessExecutionTask;
import ru.system.thundercloud.engine.db.repository.ProcessExecutionTaskRepository;
import ru.system.thundercloud.engine.db.service.TCLExecutionService;
import ru.system.thundercloud.engine.db.service.TCLProcessService;
import ru.system.thundercloud.engine.db.service.TCLTaskService;
import ru.system.thundercloud.engine.db.service.TCLVariableService;
import ru.system.thundercloud.engine.db.tables.TCLExecution;
import ru.system.thundercloud.engine.db.tables.TCLProcess;
import ru.system.thundercloud.engine.db.tables.TCLTask;
import ru.system.thundercloud.engine.db.tables.TCLVariable;
import ru.system.thundercloud.engine.exceptions.ExecutionNotFoundById;
import ru.system.thundercloud.engine.service.ThunderCloudEngine;
import ru.system.thundercloud.engine.service.process.ThunderCloudExecution;
import ru.system.thundercloud.engine.service.process.ThunderCloudProcess;
import ru.system.thundercloud.engine.service.process.ThunderCloudVariableMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author DRakovskiy
 */

@Service
public class ThunderCloudDataBaseEngine {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ThunderCloudDataBaseEngine.class);

    private final TCLExecutionService tclExecutionService;
    private final TCLProcessService tclProcessService;
    private final TCLTaskService tclTaskService;
    private final TCLVariableService tclVariableService;
    private final ProcessExecutionTaskRepository processExecutionTaskRepository;

    public ThunderCloudDataBaseEngine(TCLExecutionService tclExecutionService,
                                      TCLProcessService tclProcessService,
                                      TCLTaskService tclTaskService,
                                      ProcessExecutionTaskRepository processExecutionTaskRepository,
                                      TCLVariableService tclVariableService) {
        this.tclTaskService = tclTaskService;
        this.tclExecutionService = tclExecutionService;
        this.tclProcessService = tclProcessService;
        this.processExecutionTaskRepository = processExecutionTaskRepository;
        this.tclVariableService = tclVariableService;
    }

    public void checkProcessesInDatabase(Map<String, ThunderCloudProcess> processMap) {
        tclProcessService.checkProcessesInDatabase(processMap);
    }

    public TCLProcess getProcessByName(String processName) {
        return tclProcessService.getProcessByName(processName);
    }

    public ProcessExecutionTask getProcessExecutionTaskByExecutionId(String executionId) {
        return processExecutionTaskRepository.findProcessesExecutionsAndTasks(executionId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteCompletedTasksAndExecutions() {
        tclTaskService.deleteCompletedTasks();
        tclVariableService.deleteVariableNotActualExecutions();
        tclExecutionService.deleteNotActualExecutions();

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void setNewGetawayForTask(String executionId, String getaway, Boolean completed) {
        tclTaskService.updateTaskOnNewGetaway(getaway, completed,  executionId);
    }

    public TCLExecution executionById(String executionId) {
        return tclExecutionService
                .findExecutionById(executionId)
                .orElseThrow(() -> new ExecutionNotFoundById("По данноу id=" + executionId + " не найдено активных исполнителей"));
    }

    @Transactional
    public TCLExecution createExecution(ThunderCloudProcess process) {

        TCLProcess tclProcessEntity = getProcessByName(process.getName());

        ThunderCloudExecution execution = process.getExecution();

        String tclExecutionId = UUID.randomUUID().toString();

        TCLExecution tclExecutionEntity = new TCLExecution(
                tclExecutionId,
                execution.getName(),
                tclProcessEntity.id()
        );

        tclExecutionService.insertNewExecution(tclExecutionEntity);

        TCLTask tclTaskEntity = new TCLTask(
                UUID.randomUUID().toString(),
                execution.getStartGetaway(),
                false,
                tclExecutionId
        );

        tclTaskService.insertNewTask(tclTaskEntity);

        return tclExecutionEntity;
    }

    public void saveTCLVariableForThisProcess(List<TCLVariable> variables) throws IOException {
        tclVariableService.saveVariables(variables);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveTCLVariableForThisProcessInNewTransaction(ThunderCloudVariableMap tclVariablesMap) throws IOException {
        tclVariableService.saveVariables(variables);
    }

    public ThunderCloudVariableMap getTCLVariablesForThisExecution(String executionId) {
        return new ThunderCloudVariableMap(tclVariableService.getTCLVariablesForThisExecution(executionId));
    }

}
