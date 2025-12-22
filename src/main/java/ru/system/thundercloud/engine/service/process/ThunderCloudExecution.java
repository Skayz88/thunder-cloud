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
public class ThunderCloudExecution {
    private String id;
    private String name;
    private List<ThunderCloudGetaway> getaways;

    public ThunderCloudExecution(String name, List<ThunderCloudGetaway> getaways) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.getaways = getaways;
    }

    public String getName() {
        return name;
    }

    public static ThunderCloudExecutionCreator creator() {
        return new ThunderCloudExecutionCreator();
    }

    public static class ThunderCloudExecutionCreator {
        private String name;
        private final List<ThunderCloudGetaway> getaways;

        private ThunderCloudExecutionCreator() {
            getaways = new ArrayList<>();
        }

        public ThunderCloudExecutionCreator name(String name) {
            this.name = name;
            return this;
        }

        public ThunderCloudExecutionCreator getaway(ThunderCloudGetaway getaway) {
            getaways.add(getaway);
            return this;
        }

        public ThunderCloudExecution create() {
            return new ThunderCloudExecution(name, getaways);
        }
    }

    public List<ThunderCloudGetaway> getGetaways() {
        return getaways;
    }
}
