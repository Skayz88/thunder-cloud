package ru.system.thundercloud.engine.db.config;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.system.thundercloud.engine.service.ThunderCloudEngine;

/**
 *
 * @author DRakovskiy
 */

@Configuration
public class AfterMigrationConfig {

    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy(ThunderCloudEngine engine) {
        return flyway -> {
            flyway.migrate();
            engine.onMigrationComplete();
        };
    }
}
