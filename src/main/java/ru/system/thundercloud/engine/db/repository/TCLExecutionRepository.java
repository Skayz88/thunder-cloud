package ru.system.thundercloud.engine.db.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.system.thundercloud.engine.db.tables.TCLExecution;

/**
 *
 * @author DRakovskiy
 */
public interface TCLExecutionRepository extends ListCrudRepository<TCLExecution, String> {
}
