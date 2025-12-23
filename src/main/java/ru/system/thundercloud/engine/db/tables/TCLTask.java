package ru.system.thundercloud.engine.db.tables;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 *
 * @author DRakovskiy
 */
@Table(name = "tcl_task")
public record TCLTask(
        @Id String id,
        String name,
        Boolean completed,
        Instant expires_at,
        String execution_id
) {
}
