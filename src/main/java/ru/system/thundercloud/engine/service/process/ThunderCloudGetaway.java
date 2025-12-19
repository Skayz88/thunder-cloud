package ru.system.thundercloud.engine.service.process;

import java.util.LinkedList;

/**
 *
 * @author DRakovskiy
 */
public class ThunderCloudGetaway {
    private String id;
    private String name;
    private String position;
    private Boolean completed;
    private String execution_id;
    private LinkedList<ThunderCloudDelegate> delegates;
}
