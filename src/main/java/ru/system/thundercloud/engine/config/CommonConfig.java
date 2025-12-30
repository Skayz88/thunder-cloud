package ru.system.thundercloud.engine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author DRakovskiy
 */
@EnableTransactionManagement
@EnableAsync
@Configuration
public class CommonConfig {
}
