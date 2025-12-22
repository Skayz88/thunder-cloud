package ru.system.thundercloud.service.config.process;

import ru.system.thundercloud.engine.service.delegate.ThunderCloudEndDelegate;
import ru.system.thundercloud.engine.service.delegate.ThunderCloudStartDelegate;
import ru.system.thundercloud.engine.service.process.ThunderCloudDelegate;
import ru.system.thundercloud.engine.service.process.ThunderCloudExecution;
import ru.system.thundercloud.engine.service.process.ThunderCloudGetaway;
import ru.system.thundercloud.engine.service.process.ThunderCloudProcess;
import ru.system.thundercloud.engine.service.process.ThunderCloudTask;

/**
 *
 * @author DRakovskiy
 */

public class ConfigThunderCloudProcess {

    private ThunderCloudTask tasks = ThunderCloudTask
            .creator()
            .name("task-1")
            .delegates(ThunderCloudDelegate.creator()
                    .add(new ThunderCloudStartDelegate())
                    .add(new ThunderCloudEndDelegate())
                    .create())
            .create();

    private ThunderCloudGetaway getaway = ThunderCloudGetaway
            .creator()
            .name("getaway-1")
            .task(tasks)
            .create();

    private ThunderCloudExecution execution = ThunderCloudExecution
            .creator()
            .name("execution-1")
            .getaway(getaway)
            .create();

    private ThunderCloudProcess process = new ThunderCloudProcess("process", execution);





}
