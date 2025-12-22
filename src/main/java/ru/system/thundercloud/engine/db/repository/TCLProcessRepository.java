package ru.system.thundercloud.engine.db.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import ru.system.thundercloud.engine.db.tables.TCLProcess;

import java.util.Optional;

/**
 *
 * @author DRakovskiy
 */
public interface TCLProcessRepository extends ListCrudRepository<TCLProcess, String> {


    // Метод для вставки с контролем конфликтов
    @Query(value = """
        INSERT INTO tcl_process(id, name)
        VALUES(:id, :name)
        ON CONFLICT(name) DO NOTHING
    """)
    int insertWithConflictHandling(@Bind("id") String id, @Bind("name") String name);

    // Метод для проверки существования записи по id
    boolean existsByIdAndName(String id, String name);

    // Метод для проверки существования записи по имени
    boolean existsByName(String name);

    // Новый метод для возврата id по имени
    Optional<String> findIdByName(String name);


}
