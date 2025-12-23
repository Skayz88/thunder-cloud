package ru.system.thundercloud.engine.service.process;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author DRakovskiy
 */
public class ThunderCloudGetaway {
    private String id;
    private String name;
    private String nextGetaway;
    private Map<String, ThunderCloudTask> tasks;

    public ThunderCloudGetaway(String name, String nextGetaway, Map<String, ThunderCloudTask> tasks) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.nextGetaway = nextGetaway;
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public static ThunderCloudGetawayCreator creator() {
        return new ThunderCloudGetawayCreator();
    }

    public Map<String, ThunderCloudTask> getTasks() {
        return tasks;
    }

    public static class ThunderCloudGetawayCreator {
        private String name;
        private String nextGetaway;
        private Map<String, ThunderCloudTask> tasks;

        private ThunderCloudGetawayCreator() {
            tasks = new HashMap<>();
        }

        public ThunderCloudGetawayCreator name(String name) {
            this.name = name;
            return this;
        }

        public ThunderCloudGetawayCreator task(ThunderCloudTask task) {
            tasks.put(task.getName(), task);
            return this;
        }

        public ThunderCloudGetawayCreator nextGetaway(String nextGetaway) {
            this.nextGetaway = nextGetaway;
            return this;
        }

        public ThunderCloudGetaway create() {
            return new ThunderCloudGetaway(name, nextGetaway, tasks);
        }
    }
}
