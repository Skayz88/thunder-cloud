package ru.system.thundercloud.engine.db.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.system.thundercloud.engine.db.tables.TCLTask;

/**
 *
 * @author DRakovskiy
 */
public interface TCLTaskRepository extends ListCrudRepository<TCLTask, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tcl_task(id, name, completed, execution_id) VALUES(:id, :name, :completed, :execution_id)")
    void insert(@Param("id") String id,
                @Param("name") String name,
                @Param("completed") Boolean completed,
                @Param("execution_id") String execution_id);


    @Transactional
    @Modifying
    @Query(value = "UPDATE tcl_task set name = :name, completed = :completed WHERE execution_id = :execution_id")
    void updateTaskOnNewGetaway(@Param("name") String name,
                                @Param("completed") Boolean completed,
                                @Param("execution_id") String execution_id);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tcl_task WHERE completed = TRUE")
    void deleteCompletedTasks();

}
