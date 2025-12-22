package ru.system.thundercloud.engine.service.process;

import java.util.LinkedList;
import java.util.UUID;

/**
 *
 * @author DRakovskiy
 */
public class ThunderCloudTask {

    private String id;
    private String name;
    private LinkedList<ThunderCloudDelegate> delegates =  new LinkedList<>();

    public ThunderCloudTask(String name, LinkedList<ThunderCloudDelegate> delegates) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.delegates = delegates;
    }

    public static ThunderCloudTaskCreator creator() {
        return new ThunderCloudTaskCreator();
    }

    public static class ThunderCloudTaskCreator {
        private String name;
        private LinkedList<ThunderCloudDelegate> delegates =  new LinkedList<>();

        public ThunderCloudTaskCreator() {}

        public ThunderCloudTaskCreator name(String name) {
            this.name = name;
            return this;
        }

        public ThunderCloudTaskCreator delegates(LinkedList<ThunderCloudDelegate> delegates) {
            this.delegates = delegates;
            return this;
        }

        public ThunderCloudTask create() {
            return new ThunderCloudTask(name, delegates);
        }
    }


}
