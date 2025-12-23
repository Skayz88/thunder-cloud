/*
 * VTB Group. Do not reproduce without permission in writing.
 *
 * Copyright (c) 2025 VTB Group. All rights reserved.
 */

package ru.system.thundercloud.service.delegate;


import ru.system.thundercloud.engine.service.process.ThunderCloudDelegate;

import java.util.Map;
import java.util.UUID;

/**
 *
 * @author DRakovskiy
 */
public class ThunderCloudStartDelegate extends ThunderCloudDelegate {

    public ThunderCloudStartDelegate() {
        super("ThunderCloudStartDelegate");
    }

    @Override
    public void execute(Map<String, Object> tclVariableMap) {
        System.out.println("ThunderCloudStartDelegate.execute().start()");
        System.out.println("tclVariableMap.old="+tclVariableMap);
        tclVariableMap.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        System.out.println("tclVariableMap.new="+tclVariableMap);
        String s = tclVariableMap.get("Var1").toString();
        Integer integer = Integer.valueOf(s);
        integer = integer.intValue() + 1;
        tclVariableMap.put("Var1", String.valueOf(integer));
        System.out.println("ThunderCloudStartDelegate.execute().end()");
    }
}
