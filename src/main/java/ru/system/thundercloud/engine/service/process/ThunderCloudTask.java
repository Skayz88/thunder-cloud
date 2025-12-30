package ru.system.thundercloud.engine.service.process;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import static ru.system.thundercloud.engine.util.Constants.THUNDER_CLOUD_END_GETAWAY;

/**
 *
 * @author DRakovskiy
 */
public class ThunderCloudTask {

    private String id;
    private String name;
    private String nextGetaway;
    private Long timerMinutes;
    private List<ThunderCloudDelegateWithSupplier> delegates;

    public ThunderCloudTask(String name, List<ThunderCloudDelegateWithSupplier> delegates, String nextGetaway, Long timerMinutes) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.delegates = delegates;
        this.nextGetaway = nextGetaway;
        this.timerMinutes = timerMinutes;
    }

    public String getName() {
        return name;
    }

    public List<ThunderCloudDelegateWithSupplier> getDelegates() {
        return delegates;
    }

    public String getNextGetaway() {
        return nextGetaway;
    }

    public Long getTimerMinutes() {
        return timerMinutes;
    }

    public static ThunderCloudTaskCreator creator() {
        return new ThunderCloudTaskCreator();
    }

    public static class ThunderCloudTaskCreator {
        private String name;
        private String nextGetaway = THUNDER_CLOUD_END_GETAWAY;
        private Long timerMinutes = 2L;
        private List<ThunderCloudDelegateWithSupplier> delegates = new ArrayList<>();

        public ThunderCloudTaskCreator() {
        }

        public ThunderCloudTaskCreator name(String name) {
            this.name = name;
            return this;
        }

        public ThunderCloudTaskCreator delegate(ThunderCloudDelegate delegate, Function<ThunderCloudVariableMap, Boolean> function) {
            this.delegates.add(
                    new ThunderCloudDelegateWithSupplier(delegate, function)
            );
            return this;
        }

        public ThunderCloudTaskCreator delegate(ThunderCloudDelegate delegate) {
            this.delegates.add(
                    new ThunderCloudDelegateWithSupplier(delegate, tcvm -> true)
            );
            return this;
        }

        public ThunderCloudTaskCreator nextGetaway(String nextGetaway) {
            this.nextGetaway = nextGetaway;
            return this;
        }

        public ThunderCloudTaskCreator nextGetaway(String nextGetaway, Long timerMinutes) {
            this.nextGetaway = nextGetaway;
            this.timerMinutes = timerMinutes;
            return this;
        }

        public ThunderCloudTask create() {
            return new ThunderCloudTask(name, delegates, nextGetaway, timerMinutes);
        }
    }


}
