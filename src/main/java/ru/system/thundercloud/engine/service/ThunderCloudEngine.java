package ru.system.thundercloud.engine.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.system.thundercloud.engine.service.process.ThunderCloudGetaway;
import ru.system.thundercloud.engine.service.process.ThunderCloudProcess;
import ru.system.thundercloud.engine.service.process.ThunderCloudTask;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DRakovskiy
 */
@Service
public class ThunderCloudEngine {

    private Map<String, ThunderCloudProcess> processMap;

    public ThunderCloudEngine(List<ThunderCloudProcess> processes) {
        processMap =  new HashMap<>();
        for (ThunderCloudProcess process : processes) {
            processMap.put(process.getName(), process);
        }
    }

    @PostConstruct
    public void init() {
        System.out.println("ThunderCloudEngine init...");
        System.out.println("ThunderCloudProcess count = " + processMap.size());
    }

    public void startProcess(String processName) {
        ThunderCloudProcess process = processMap.get(processName);
        if (process == null) {
            System.out.println("ThunderCloudEngine process not found: " + processName);
            return;
        }

        List<ThunderCloudGetaway> getaways = process.getExecution().getGetaways();

        for (ThunderCloudGetaway getaway : getaways) {
            List<String> taskList = getaway.getTaskListName();
            Map<String, ThunderCloudTask> tasks = getaway.getTasks();
            for (String task : taskList) {
                System.out.println("ThunderCloudEngine task: " + task);
                ThunderCloudTask cloudTask = tasks.get(task);
                cloudTask.getDelegates().forEach(delegate -> {
                    delegate.execute();
                });
            }
        }

    }


}
