package ru.system.thundercloud.engine.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.system.thundercloud.engine.service.process.ThunderCloudGetaway;
import ru.system.thundercloud.engine.service.process.ThunderCloudProcess;
import ru.system.thundercloud.engine.service.process.ThunderCloudTask;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author DRakovskiy
 */
@Service
public class ThunderCloudEngine {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ThunderCloudEngine.class);

    private final Map<String, ThunderCloudProcess> processMap;

    public ThunderCloudEngine(List<ThunderCloudProcess> processes) {
        processMap = new HashMap<>();
        for (ThunderCloudProcess process : processes) {
            processMap.put(process.getName(), process);
        }
    }

    @PostConstruct
    public void init() {
        log.info("init...");
        log.info("version: {}", "0.0.1");
        if (Objects.nonNull(processMap) && !processMap.isEmpty()) {
            log.info("processes: {}", Arrays.toString(processMap.keySet().toArray()));
        }

    }

    public void onMigrationComplete() {
        log.info("Выполнена задача после миграции");
    }

    public void startProcess(String processName) {
        ThunderCloudProcess process = processMap.get(processName);
        if (process == null) {
            System.out.println("ThunderCloudEngine process not found: " + processName);
            return;
        }

        List<ThunderCloudGetaway> getaways = process.getExecution().getGetaways();

        for (ThunderCloudGetaway getaway : getaways) {
            System.out.println("ThunderCloudGetaway- " + getaway.getName());
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
