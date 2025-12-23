package ru.system.thundercloud.engine.db.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.system.thundercloud.engine.db.tables.TCLVariable;

import java.util.List;

/**
 *
 * @author DRakovskiy
 */
public interface TCLVariableRepository extends ListCrudRepository<TCLVariable, String> {

    @Query("SELECT * FROM tcl_variable WHERE execution_id = :executionId")
    List<TCLVariable> findByExecutionId(String executionId);

    @Modifying
    @Transactional
    @Query("DELETE FROM tcl_variable WHERE execution_id NOT IN "
            + " (SELECT DISTINCT execution_id FROM tcl_task) ")
    void deleteVariableNotActualExecutions();


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tcl_variable (id, key, value, execution_id)"
            + "VALUES (:id, :key, :value, :execution_id) "
            + "ON CONFLICT (id) DO UPDATE "
            + "SET value = EXCLUDED.value")
    void insert(@Param("id") String id,
                @Param("key") String key,
                @Param("value") String value,
                @Param("execution_id") String execution_id);

    List<TCLVariable> findTCLVariableByExecutionId(String executionId);
}
