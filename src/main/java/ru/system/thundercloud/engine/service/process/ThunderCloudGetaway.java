package ru.system.thundercloud.engine.service.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author DRakovskiy
 */
public class ThunderCloudGetaway {
    private String id;
    private String name;
    private List<String> taskListName;
    private Map<String, ThunderCloudTask> tasks;

    public ThunderCloudGetaway(String name, List<String> taskListName, Map<String, ThunderCloudTask> tasks) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.taskListName = taskListName;
        this.tasks = tasks;
    }


    public static ThunderCloudGetawayCreator creator() {
        return new ThunderCloudGetawayCreator();
    }

    public static class ThunderCloudGetawayCreator {
        private String name;
        private List<String> taskListName;
        private Map<String, ThunderCloudTask> tasks;

        private ThunderCloudGetawayCreator() {
            taskListName = new ArrayList<>();
            tasks = new HashMap<>();
        }

        public ThunderCloudGetawayCreator name(String name) {
            this.name = name;
            return this;
        }

        public ThunderCloudGetawayCreator task(ThunderCloudTask task) {
            taskListName.add(task.getName());
            tasks.put(task.getName(), task);
            return this;
        }

        public ThunderCloudGetaway create() {
            return new ThunderCloudGetaway(name, taskListName, tasks);
        }
    }
}
