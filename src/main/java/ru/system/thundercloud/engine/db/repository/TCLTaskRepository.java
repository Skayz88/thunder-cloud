package ru.system.thundercloud.engine.db.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.system.thundercloud.engine.db.tables.TCLTask;

/**
 *
 * @author DRakovskiy
 */
public interface TCLTaskRepository extends ListCrudRepository<TCLTask, String> {
}
