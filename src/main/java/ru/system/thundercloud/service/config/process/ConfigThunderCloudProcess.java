package ru.system.thundercloud.service.config.process;

import ru.system.thundercloud.engine.service.delegate.ThunderCloudEndDelegate;
import ru.system.thundercloud.engine.service.delegate.ThunderCloudStartDelegate;
import ru.system.thundercloud.engine.service.process.ThunderCloudDelegate;
import ru.system.thundercloud.engine.service.process.ThunderCloudTask;

/**
 *
 * @author DRakovskiy
 */

public class ConfigThunderCloudProcess {



    private ThunderCloudTask test_tasks_1 = ThunderCloudTask
            .creator()
            .name("test_task_1")
            .delegates(ThunderCloudDelegate.creator()
                    .add(new ThunderCloudStartDelegate())
                    .add(new ThunderCloudEndDelegate())
                    .create())
            .create();

}
