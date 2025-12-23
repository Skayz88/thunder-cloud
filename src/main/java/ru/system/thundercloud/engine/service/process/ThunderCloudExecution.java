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
    private String startGetaway;
    private Map<String, ThunderCloudGetaway> getaways;

    public ThunderCloudExecution(String name,  Map<String, ThunderCloudGetaway> getaways, String startGetaway) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.getaways = getaways;
        this.startGetaway = startGetaway;
    }

    public String getName() {
        return name;
    }

    public static ThunderCloudExecutionCreator creator() {
        return new ThunderCloudExecutionCreator();
    }

    public static class ThunderCloudExecutionCreator {
        private String name;
        private String startGetaway;
        private final  Map<String, ThunderCloudGetaway> getaways;

        private ThunderCloudExecutionCreator() {
            getaways = new HashMap<>();
        }

        public ThunderCloudExecutionCreator name(String name) {
            this.name = name;
            return this;
        }

        public ThunderCloudExecutionCreator getaway(ThunderCloudGetaway getaway) {
            getaways.put(getaway.getName(), getaway);
            return this;
        }

        public ThunderCloudExecutionCreator startGetaway(String startGetaway) {
            this.startGetaway = startGetaway;
            return this;
        }

        public ThunderCloudExecution create() {
            return new ThunderCloudExecution(name, getaways, startGetaway);
        }
    }

    public Map<String, ThunderCloudGetaway> getGetaways() {
        return getaways;
    }

    public String getStartGetaway() {
        return startGetaway;
    }
}
