package ru.system.thundercloud.engine.service.process;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author DRakovskiy
 */
public class ThunderCloudTask {

    private String id;
    private String name;
    private String nextGetaway;
    private List<ThunderCloudDelegate> delegates;

    public ThunderCloudTask(String name, List<ThunderCloudDelegate> delegates, String nextGetaway) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.delegates = delegates;
        this.nextGetaway = nextGetaway;
    }

    public String getName() {
        return name;
    }

    public List<ThunderCloudDelegate> getDelegates() {
        return delegates;
    }

    public String getNextGetaway() {
        return nextGetaway;
    }

    public static ThunderCloudTaskCreator creator() {
        return new ThunderCloudTaskCreator();
    }

    public static class ThunderCloudTaskCreator {
        private String name;
        private String nextGetaway;
        private List<ThunderCloudDelegate> delegates = new ArrayList<>();

        public ThunderCloudTaskCreator() {
        }

        public ThunderCloudTaskCreator name(String name) {
            this.name = name;
            return this;
        }

        public ThunderCloudTaskCreator delegates(List<ThunderCloudDelegate> delegates) {
            this.delegates = delegates;
            return this;
        }

        public ThunderCloudTaskCreator nextGetaway(String nextGetaway) {
            this.nextGetaway = nextGetaway;
            return this;
        }

        public ThunderCloudTask create() {
            return new ThunderCloudTask(name, delegates, nextGetaway);
        }
    }


}
