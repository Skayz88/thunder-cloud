package ru.system.thundercloud.engine.service.delegate;

import ru.t1.learning.data_jdbc.jdbc_project.engine.service.process.ThunderCloudDelegate;

/**
 *
 * @author DRakovskiy
 */
public class ThunderCloudStartDelegate extends ThunderCloudDelegate {

    public ThunderCloudStartDelegate() {
        super("ThunderCloudStartDelegate");
    }

    @Override
    public void execute() {
        System.out.println("ThunderCloudStartDelegate.execute()");
    }
}
