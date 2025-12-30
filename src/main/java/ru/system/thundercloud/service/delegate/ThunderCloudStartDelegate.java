/*
 * VTB Group. Do not reproduce without permission in writing.
 *
 * Copyright (c) 2025 VTB Group. All rights reserved.
 */

package ru.system.thundercloud.service.delegate;


import ch.qos.logback.core.util.TimeUtil;
import org.springframework.stereotype.Component;
import ru.system.thundercloud.engine.service.process.ThunderCloudDelegate;
import ru.system.thundercloud.engine.service.process.ThunderCloudVariableMap;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author DRakovskiy
 */

@Component
public class ThunderCloudStartDelegate extends ThunderCloudDelegate {

    public ThunderCloudStartDelegate() {
        super("ThunderCloudStartDelegate");
    }

    @Override
    public void execute(ThunderCloudVariableMap tclVariableMap) {
        Integer intRundom = ThreadLocalRandom.current().nextInt(300, 500);
        System.out.println("ThunderCloudStartDelegate.execute().start()");
        //System.out.println("tclVariableMap.old="+tclVariableMap);
        String s = tclVariableMap.get("Var1").toString();
        Integer integer = Integer.valueOf(s);
        integer = integer.intValue() + intRundom;
        tclVariableMap.put("Var1", String.valueOf(integer));
       // System.out.println("tclVariableMap.new="+tclVariableMap.toString());
        System.out.println("ThunderCloudStartDelegate.execute().end()");
        try {

            TimeUnit.MILLISECONDS.sleep(intRundom);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
