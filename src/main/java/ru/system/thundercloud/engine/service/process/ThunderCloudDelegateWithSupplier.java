package ru.system.thundercloud.engine.service.process;

import java.util.function.Function;

/**
 *
 * @author DRakovskiy
 */

public class ThunderCloudDelegateWithSupplier {

    public ThunderCloudDelegate delegate;
    public Function<ThunderCloudVariableMap, Boolean> function;

    public ThunderCloudDelegateWithSupplier(ThunderCloudDelegate delegate, Function<ThunderCloudVariableMap, Boolean> function) {
        this.delegate = delegate;
        this.function = function;
    }

    public void execute(ThunderCloudVariableMap tclVariableMap) {
        if (Boolean.TRUE.equals(function.apply(tclVariableMap))) delegate.execute(tclVariableMap);
    }

    public static ThunderCloudDelegateCreator creator() {
        return new ThunderCloudDelegateCreator();
    }


    public static class ThunderCloudDelegateCreator {

        public ThunderCloudDelegate delegate;
        public Function<ThunderCloudVariableMap, Boolean> function;

        public ThunderCloudDelegateCreator() {
        }

        public ThunderCloudDelegateCreator delegate(ThunderCloudDelegate delegate) {
            this.delegate = delegate;
            return this;
        }

        public ThunderCloudDelegateCreator function(Function<ThunderCloudVariableMap, Boolean> function) {
            this.function = function;
            return this;
        }

        public ThunderCloudDelegateWithSupplier create() {
            return new ThunderCloudDelegateWithSupplier(delegate, function);
        }
    }

}
