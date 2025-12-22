package ru.system.thundercloud.engine.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.system.thundercloud.engine.db.ThunderCloudDataBaseEngine;
import ru.system.thundercloud.engine.db.service.TCLExecutionService;
import ru.system.thundercloud.engine.db.service.TCLProcessService;
import ru.system.thundercloud.engine.db.tables.TCLExecution;
import ru.system.thundercloud.engine.db.tables.TCLProcess;
import ru.system.thundercloud.engine.exceptions.ProcessNotFoundException;
import ru.system.thundercloud.engine.service.process.ThunderCloudExecution;
import ru.system.thundercloud.engine.service.process.ThunderCloudGetaway;
import ru.system.thundercloud.engine.service.process.ThunderCloudProcess;
import ru.system.thundercloud.engine.service.process.ThunderCloudTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

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
    }

    public void onMigrationComplete() {
        log.debug("Проверка заполнения таблицы процессов");
        thunderCloudDataBaseEngine.checkProcessesInDatabase(processMap);
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

    public String startNewExecutionForProcess(String processName) {

        ThunderCloudProcess process = getProcessIfIsPresentOrError(processName);

        TCLExecution tclExecution = thunderCloudDataBaseEngine.createExecution(process);

        return tclExecution.id();

//        List<ThunderCloudGetaway> getaways = process.getExecution().getGetaways();
//
//        for (ThunderCloudGetaway getaway : getaways) {
//            System.out.println("ThunderCloudGetaway- " + getaway.getName());
//            List<String> taskList = getaway.getTaskListName();
//            Map<String, ThunderCloudTask> tasks = getaway.getTasks();
//            for (String task : taskList) {
//                System.out.println("ThunderCloudEngine task: " + task);
//                ThunderCloudTask cloudTask = tasks.get(task);
//                cloudTask.getDelegates().forEach(delegate -> {
//                    delegate.execute();
//                });
//            }
//        }

    }


}
