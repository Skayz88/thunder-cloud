package ru.system.thundercloud.engine.db.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import ru.system.thundercloud.engine.db.dto.ProcessExecutionTask;

/**
 *
 * @author DRakovskiy
 */
public interface ProcessExecutionTaskRepository extends ListCrudRepository<ProcessExecutionTask, String> {

    @Query("SELECT p.id AS process_id, p.name AS process_name," +
            "       e.id AS execution_id, e.name AS execution_name," +
            "       t.id AS task_id, t.name AS task_name, t.completed" +
            " FROM tcl_process p" +
            " LEFT JOIN tcl_execution e ON p.id = e.process_id" +
            " LEFT JOIN tcl_task t ON e.id = t.execution_id" +
            " WHERE e.id = :execution_id")
    ProcessExecutionTask findProcessesExecutionsAndTasks(@Param("execution_id") String execution_id);
}
