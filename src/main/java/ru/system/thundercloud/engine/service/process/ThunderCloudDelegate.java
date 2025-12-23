package ru.system.thundercloud.engine.service.process;

import java.util.LinkedList;
import java.util.Map;

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

    public static ThunderCloudDelegateListCreator creator() {
        return new ThunderCloudDelegateListCreator();
    }

    public static class ThunderCloudDelegateListCreator {
        private LinkedList<ThunderCloudDelegate> delegates;

        public ThunderCloudDelegateListCreator() {
            delegates = new LinkedList<>();
        }

        public ThunderCloudDelegateListCreator add(ThunderCloudDelegate delegate) {
            this.delegates.add(delegate);
            return this;
        }

        public LinkedList<ThunderCloudDelegate> create() {
            return delegates;
        }
    }

}
