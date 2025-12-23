package ru.system.thundercloud.service.config.process;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.system.thundercloud.engine.service.delegate.ThunderCloudEndDelegate;
import ru.system.thundercloud.engine.service.delegate.ThunderCloudStartDelegate;
import ru.system.thundercloud.engine.service.process.ThunderCloudDelegate;
import ru.system.thundercloud.engine.service.process.ThunderCloudExecution;
import ru.system.thundercloud.engine.service.process.ThunderCloudGetaway;
import ru.system.thundercloud.engine.service.process.ThunderCloudProcess;
import ru.system.thundercloud.engine.service.process.ThunderCloudTask;

import static ru.system.thundercloud.util.Constants.THUNDER_CLOUD_END_GETAWAY;

/**
 *
 * @author DRakovskiy
 */

@Configuration
public class ConfigThunderCloudProcess {

    @Bean
    public ThunderCloudProcess getThunderCloudProcess() {

        ThunderCloudTask tasks = ThunderCloudTask
                .creator()
                .name("task-1")
                .nextGetaway("getaway-2")
                .delegates(ThunderCloudDelegate.creator()
                        .add(new ThunderCloudStartDelegate())
                        .add(new ThunderCloudEndDelegate())
                        .create())
                .create();

        ThunderCloudTask tasks_2 = ThunderCloudTask
                .creator()
                .name("task-1")
                .nextGetaway("getaway-3")
                .delegates(ThunderCloudDelegate.creator()
                        .add(new ThunderCloudStartDelegate())
                        .add(new ThunderCloudEndDelegate())
                        .create())
                .create();

        ThunderCloudTask tasks_3 = ThunderCloudTask
                .creator()
                .name("task-1")
                .nextGetaway(THUNDER_CLOUD_END_GETAWAY)
                .delegates(ThunderCloudDelegate.creator()
                        .add(new ThunderCloudStartDelegate())
                        .add(new ThunderCloudEndDelegate())
                        .create())
                .create();

        ThunderCloudGetaway getaway = ThunderCloudGetaway
                .creator()
                .name("getaway-1")
                .task(tasks)
                .create();

        ThunderCloudGetaway getaway2 = ThunderCloudGetaway
                .creator()
                .name("getaway-2")
                .task(tasks_2)
                .create();

        ThunderCloudGetaway getaway3 = ThunderCloudGetaway
                .creator()
                .name("getaway-3")
                .task(tasks_3)
                .create();

        ThunderCloudExecution execution = ThunderCloudExecution
                .creator()
                .name("execution-1")
                .startGetaway("getaway-1")
                .getaway(getaway)
                .getaway(getaway2)
                .getaway(getaway3)
                .create();

        return new ThunderCloudProcess("process-1", execution);

    }


}
