package ru.system.thundercloud.engine.service.process;

/**
 *
 * @author DRakovskiy
 */

public abstract class ThunderCloudDelegate {

    public final String name;

    public abstract void execute(ThunderCloudVariableMap tclVariableMap);

    public String getName() {
        return name;
    }

    public ThunderCloudDelegate(String name) {
        this.name = name;
    }

}
