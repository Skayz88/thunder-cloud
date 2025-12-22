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
                .task(tasks)
                .create();

        ThunderCloudGetaway getaway3 = ThunderCloudGetaway
                .creator()
                .name("getaway-3")
                .task(tasks)
                .create();

        ThunderCloudExecution execution = ThunderCloudExecution
                .creator()
                .name("execution-1")
                .getaway(getaway)
                .getaway(getaway2)
                .getaway(getaway3)
                .create();

        return new ThunderCloudProcess("process", execution);

    }


}
