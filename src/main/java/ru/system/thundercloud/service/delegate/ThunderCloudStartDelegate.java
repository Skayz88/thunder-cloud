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
public class ThunderCloudStartDelegate extends ThunderCloudDelegate {

    public ThunderCloudStartDelegate() {
        super("ThunderCloudStartDelegate");
    }

    @Override
    public void execute() {
        System.out.println("ThunderCloudStartDelegate.execute()");
    }
}
