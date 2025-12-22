package ru.system.thundercloud.engine.service.delegate;

import ru.system.thundercloud.engine.service.process.ThunderCloudDelegate;

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
