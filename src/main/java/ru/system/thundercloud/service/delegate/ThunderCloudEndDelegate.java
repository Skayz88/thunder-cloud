/*
 * VTB Group. Do not reproduce without permission in writing.
 *
 * Copyright (c) 2025 VTB Group. All rights reserved.
 */

package ru.system.thundercloud.service.delegate;

import ru.system.thundercloud.engine.service.process.ThunderCloudDelegate;
import ru.system.thundercloud.engine.service.process.ThunderCloudVariableMap;

import java.util.Map;
import java.util.UUID;

/**
 *
 * @author DRakovskiy
 */
public class ThunderCloudEndDelegate extends ThunderCloudDelegate {

    public ThunderCloudEndDelegate() {
        super("ThunderCloudEndDelegate");
    }

    @Override
    public void execute(ThunderCloudVariableMap tclVariableMap) {
        System.out.println("ThunderCloudEndDelegate.execute().start()");
        System.out.println("tclVariableMap.old="+tclVariableMap);
       //tclVariableMap.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        //System.out.println("tclVariableMap.new="+tclVariableMap);
        System.out.println("ThunderCloudEndDelegate.execute().end()");
    }
}
