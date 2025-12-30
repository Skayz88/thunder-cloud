package ru.system.thundercloud.engine.service.process;

import java.util.LinkedList;
import java.util.function.Supplier;

/**
 *
 * @author DRakovskiy
 */

public abstract class ThunderCloudDelegate {

    public final String name;

    public abstract void execute(ThunderCloudVariableMap tclVariableMap);

    public Supplier<Boolean> isRunningThisDelegate = () -> true;

    public String getName() {
        return name;
    }

    public ThunderCloudDelegate(String name) {
        this.name = name;
    }

}
