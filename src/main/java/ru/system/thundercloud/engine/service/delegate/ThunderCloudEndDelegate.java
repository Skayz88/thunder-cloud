package ru.system.thundercloud.engine.service.delegate;

import ru.t1.learning.data_jdbc.jdbc_project.engine.service.process.ThunderCloudDelegate;

/**
 *
 * @author DRakovskiy
 */
public class ThunderCloudEndDelegate extends ThunderCloudDelegate {

    public ThunderCloudEndDelegate() {
        super("ThunderCloudEndDelegate");
    }

    @Override
    public void execute() {
        System.out.println("ThunderCloudEndDelegate.execute()");
    }
}
