/*
 * VTB Group. Do not reproduce without permission in writing.
 *
 * Copyright (c) 2025 VTB Group. All rights reserved.
 */

package ru.system.thundercloud.service.delegate;

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
