package ru.system.thundercloud.engine.db.tables;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 *
 * @author DRakovskiy
 */
@Table(name = "tcl_task")
public record TCLTask(
        @Id String id,
        String name,
        Boolean completed,
        String execution_id
) {
}
