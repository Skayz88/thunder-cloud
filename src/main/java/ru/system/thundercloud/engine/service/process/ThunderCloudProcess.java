package ru.system.thundercloud.engine.service.process;

import java.util.UUID;

/**
 *
 * @author DRakovskiy
 */
public class ThunderCloudProcess {

    private String id;
    private String name;
    private ThunderCloudExecution execution;

    private ThunderCloudProcess(String id, String name, ThunderCloudExecution execution) {
        this.id = id;
        this.name = name;
        this.execution = execution;
    }

    public String getName() {
        return name;
    }

    public ThunderCloudExecution getExecution() {
        return execution;
    }

    public String getId() {
        return id;
    }

    public ThunderCloudProcess(String name, ThunderCloudExecution execution) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.execution = execution;
    }

    public static class ThunderCloudProcessCreator {
        private String name;
        private ThunderCloudExecution execution;

        public ThunderCloudProcessCreator name(String name) {
            this.name = name;
            return this;
        }

        public ThunderCloudProcessCreator execution(ThunderCloudExecution execution) {
            this.execution = execution;
            return this;
        }

        public ThunderCloudProcess create() {
            return new ThunderCloudProcess(name, execution);
        }
    }
}
