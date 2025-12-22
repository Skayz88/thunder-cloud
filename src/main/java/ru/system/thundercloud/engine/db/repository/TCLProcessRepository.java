package ru.system.thundercloud.engine.db.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import ru.system.thundercloud.engine.db.tables.TCLProcess;

/**
 *
 * @author DRakovskiy
 */
public interface TCLProcessRepository extends ListCrudRepository<TCLProcess, String> {

    // Метод для вставки с контролем конфликтов
    @Modifying
    @Query(value = """ 
            INSERT INTO tcl_process(id, name) VALUES(:id, :name) 
            """)
    void insertWithConflictHandling(@Param("id") String id, @Param("name") String name);

    // Метод для проверки существования записи по id
    boolean existsByIdAndName(String id, String name);

    // Метод для проверки существования записи по имени
    boolean existsByName(String name);
}
