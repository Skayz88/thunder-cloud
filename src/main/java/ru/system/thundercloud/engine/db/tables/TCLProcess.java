package ru.system.thundercloud.engine.db.tables;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 *
 * @author DRakovskiy
 */
@Table(name = "cl_process")
public record TCLProcess(
        @Id String id,
        String name
) {
}
