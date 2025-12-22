package ru.system.thundercloud.engine.db.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.system.thundercloud.engine.db.tables.TCLProcess;

/**
 *
 * @author DRakovskiy
 */
public interface TCLProcessRepository extends ListCrudRepository<TCLProcess, String> {
}
