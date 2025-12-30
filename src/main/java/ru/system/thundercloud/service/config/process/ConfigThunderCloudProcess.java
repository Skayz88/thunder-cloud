package ru.system.thundercloud.service.config.process;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.system.thundercloud.engine.service.process.ThunderCloudExecution;
import ru.system.thundercloud.engine.service.process.ThunderCloudGetaway;
import ru.system.thundercloud.engine.service.process.ThunderCloudProcess;
import ru.system.thundercloud.engine.service.process.ThunderCloudTask;
import ru.system.thundercloud.service.delegate.ThunderCloudEndDelegate;
import ru.system.thundercloud.service.delegate.ThunderCloudStartDelegate;

/**
 *
 * @author DRakovskiy
 */

@Configuration
@EnableTransactionManagement
@EnableAsync
public class ConfigThunderCloudProcess {

    @Bean
    public ThunderCloudProcess getThunderCloudProcess(ThunderCloudStartDelegate startDelegate, ThunderCloudEndDelegate endDelegate, DataSourceTransactionManager dataSourceTransactionManager) {

        ThunderCloudTask tasks = ThunderCloudTask
                .creator()
                .name("task-1")
                .nextGetaway("getaway-2")
                .delegate(startDelegate)
                .delegate(endDelegate)
                .create();

        ThunderCloudTask tasks_2 = ThunderCloudTask
                .creator()
                .name("task-1")
                .nextGetaway("getaway-3", 60L)
                .delegate(startDelegate, tcvm -> {
                    String v1 = tcvm.get("Var1", String.class);
                    System.out.println("!!!! Var1= " + v1);
                    return StringUtils.isNotBlank(v1);
                })
                .delegate(endDelegate)
                .create();

        ThunderCloudTask tasks_3 = ThunderCloudTask
                .creator()
                .name("task-1")
                .delegate(startDelegate)
                .delegate(endDelegate)
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
