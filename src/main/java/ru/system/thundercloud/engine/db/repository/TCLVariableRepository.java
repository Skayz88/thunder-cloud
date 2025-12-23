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

    @Query("DELETE FROM tcl_variable WHERE execution_id = :executionId")
    int deleteByExecutionId(String executionId);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tcl_variable(id, key, value, execution_id) VALUES(:id, :key, :value, :execution_id)")
    void insert(@Param("id") String id,
                @Param("key") String key,
                @Param("value") String value,
                @Param("execution_id") String execution_id);
}
