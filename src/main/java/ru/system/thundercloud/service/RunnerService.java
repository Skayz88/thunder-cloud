package ru.system.thundercloud.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.system.thundercloud.engine.service.ThunderCloudEngine;
import ru.system.thundercloud.engine.service.process.ThunderCloudProcess;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

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

    private String runProcess(ThunderCloudEngine engine, ThunderCloudProcess process, Map<String, Object> tclVariableMap) {
        return engine.startNewExecutionForProcess(process.getName(), tclVariableMap);
    }

    private CompletableFuture<String> nextStepOnFuture(Map<String, Object> tclVariableMap) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String executionId = runProcess(engine, process, tclVariableMap);
                for (int i = 1; i <= 3; i++) {
                    engine.executionTask(executionId, "task-1");
                }
                return executionId;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Starting ThunderCloud Engine");

        Map<String, Object> tclVariableMap = new HashMap<>();
        tclVariableMap.put("Var1", "1");
        tclVariableMap.put("Var2", "1000");

        for (int i = 1; i <= 5; i++) {


//            CompletableFuture<String> exec_1 = nextStepOnFuture(tclVariableMap);
//            CompletableFuture<String> exec_2 = nextStepOnFuture(tclVariableMap);
//            CompletableFuture<String> exec_3 = nextStepOnFuture(tclVariableMap);
//            CompletableFuture<String> exec_4 = nextStepOnFuture(tclVariableMap);


//            CompletableFuture<String> exec_1_1 = nextStepOnFuture(tclVariableMap);
//            CompletableFuture<String> exec_2_1 = nextStepOnFuture(tclVariableMap);
//            CompletableFuture<String> exec_3_1 = nextStepOnFuture(tclVariableMap);
//            CompletableFuture<String> exec_4_1 = nextStepOnFuture(tclVariableMap);

//            CompletableFuture<String> exec_1_2 = nextStepOnFuture(tclVariableMap);
//            CompletableFuture<String> exec_2_2 = nextStepOnFuture(tclVariableMap);

//            CompletableFuture<String> exec_3_2 = nextStepOnFuture(tclVariableMap);
//            CompletableFuture<String> exec_4_2 = nextStepOnFuture(tclVariableMap);
//
//            CompletableFuture<String> exec_1_3 = nextStepOnFuture(tclVariableMap);
//            CompletableFuture<String> exec_2_3 = nextStepOnFuture(tclVariableMap);
//            CompletableFuture<String> exec_3_3 = nextStepOnFuture(tclVariableMap);
//            CompletableFuture<String> exec_4_3 = nextStepOnFuture(tclVariableMap);

//            CompletableFuture.allOf(exec_1
//                    ,
//                    exec_2, exec_3, exec_4
//                    ,
//                    exec_1_1,exec_2_1,exec_3_1,exec_4_1
//                    ,
//                    exec_1_2,exec_2_2
//                    ,exec_3_2,exec_4_2,exec_1_3,exec_2_3,exec_3_3,exec_4_3
//                    ).join();


            //String executionId = engine.startNewExecutionForProcess(process.getName(), tclVariableMap);
            //String executionId_1 = engine.startNewExecutionForProcess(process.getName(), tclVariableMap);
            //String executionId_2 = engine.startNewExecutionForProcess(process.getName(), tclVariableMap);
            //engine.executionTask(executionId, "task-1");
            //engine.executionTask(executionId_1, "task-1");
            //engine.executionTask(executionId, "task-1");
            //engine.executionTask(executionId_2, "task-1");
            //engine.executionTask(executionId_1, "task-1");
            //engine.executionTask(executionId, "task-1");
            //engine.executionTask(executionId_1, "task-1");
            //engine.executionTask(executionId_2, "task-1");
            //engine.executionTask(executionId_2, "task-1");
        }

        //String executionId = engine.startNewExecutionForProcess(process.getName(), tclVariableMap);

        //String executionId = engine.executionTask("595c40a7-40a1-41b5-ad62-b931f2bc4873", "task-1");

        System.out.println("Приложение запущено успешно! ");
    }


}
