package ru.system.thundercloud.engine.db.tables;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 *
 * @author DRakovskiy
 */
@Table(name = "cl_execution")
public record TCLExecution(
        @Id String id,
        String name,
        String process_id
) {
}
