package ru.system.thundercloud.engine.service.process;

import java.util.LinkedList;

/**
 *
 * @author DRakovskiy
 */
public class ThunderCloudTask {

    private String id;
    private String name;
    private LinkedList<ThunderCloudDelegate> delegates =  new LinkedList<>();

    public ThunderCloudTask setName(String name) {
        this.name = name;
        return this;
    }

    public ThunderCloudTask setId(String id) {
        this.id = id;
        return this;
    }

    public ThunderCloudTask startTasks() {
        //delegates.add(null);
        return this;
    }

    public ThunderCloudTask addTasks(ThunderCloudDelegate delegate) {
        delegates.add(delegate);
        return this;
    }

    public ThunderCloudTask endTasks() {
        //delegates.add(null);
        return this;
    }


}
