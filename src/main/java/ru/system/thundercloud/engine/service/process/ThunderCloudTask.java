package ru.system.thundercloud.engine.service.process;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author DRakovskiy
 */
public class ThunderCloudTask {

    private String id;
    private String name;
    private List<ThunderCloudDelegate> delegates;

    public ThunderCloudTask(String name, List<ThunderCloudDelegate> delegates) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.delegates = delegates;
    }

    public String getName() {
        return name;
    }

    public static ThunderCloudTaskCreator creator() {
        return new ThunderCloudTaskCreator();
    }

    public static class ThunderCloudTaskCreator {
        private String name;
        private List<ThunderCloudDelegate> delegates =  new ArrayList<>();

        public ThunderCloudTaskCreator() {}

        public ThunderCloudTaskCreator name(String name) {
            this.name = name;
            return this;
        }

        public ThunderCloudTaskCreator delegates(List<ThunderCloudDelegate> delegates) {
            this.delegates = delegates;
            return this;
        }

        public ThunderCloudTask create() {
            return new ThunderCloudTask(name, delegates);
        }
    }


}
