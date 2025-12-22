package ru.system.thundercloud.engine.db.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.system.thundercloud.engine.db.tables.TCLExecution;

/**
 *
 * @author DRakovskiy
 */
public interface TCLExecutionRepository extends ListCrudRepository<TCLExecution, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tcl_execution(id, name, process_id) VALUES(:id, :name, :process_id)")
    void insert(@Param("id") String id, @Param("name") String name, @Param("process_id") String process_id);
}
