package ru.system.thundercloud.engine.db.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.system.thundercloud.engine.db.tables.TCLProcess;

import java.util.List;

/**
 *
 * @author DRakovskiy
 */
public interface TCLProcessRepository extends ListCrudRepository<TCLProcess, String> {

    // Метод для вставки с контролем конфликтов
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying
    @Query(value = "INSERT INTO tcl_process(id, name) VALUES(:id, :name)")
    void insertProcess(@Param("id") String id, @Param("name") String name);

    // Метод для проверки существования записи по имени
    boolean existsByName(String name);

    TCLProcess getTCLProcessByName(String name);
}
