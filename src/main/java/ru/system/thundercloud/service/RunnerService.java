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

//        Map<String, Object> tclVariableMap = new HashMap<>();
//        tclVariableMap.put("Var1", "1");
//        tclVariableMap.put("Var2", "1000");
//
//        for (int i = 1; i <= 5; i++) {
//            String executionId = engine.startNewExecutionForProcess(process.getName(), tclVariableMap);
//            engine.executionTask(executionId, "task-1");
//            engine.executionTask(executionId, "task-1");
//            //engine.executionTask(executionId, "task-1");
//        }

        //String executionId = engine.startNewExecutionForProcess(process.getName(), tclVariableMap);

        //String executionId = engine.executionTask("595c40a7-40a1-41b5-ad62-b931f2bc4873", "task-1");

        System.out.println("Приложение запущено успешно! " );
    }



}
