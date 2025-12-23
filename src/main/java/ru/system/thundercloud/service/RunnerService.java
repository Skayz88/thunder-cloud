package ru.system.thundercloud.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.system.thundercloud.engine.service.ThunderCloudEngine;
import ru.system.thundercloud.engine.service.process.ThunderCloudProcess;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DRakovskiy
 */
@Service
public class RunnerService implements CommandLineRunner {

    private final ThunderCloudEngine engine;
    private final ThunderCloudProcess process;

    public RunnerService(ThunderCloudEngine engine, ThunderCloudProcess process) {
        this.engine = engine;
        this.process = process;
    }

    @Override
    public void run(String... args) throws Exception {

        Map<String, Object> tclVariableMap = new HashMap<>();
        tclVariableMap.put("Var1", "1");

        //String executionId = engine.startNewExecutionForProcess(process.getName(), tclVariableMap);

        String executionId = engine.executionTask("901d343b-fe8a-4de8-a181-d3657e4c8b0d", "task-1");

        System.out.println("Приложение запущено успешно! " + executionId);
    }



}
