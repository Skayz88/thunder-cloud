package ru.system.thundercloud.engine.db.service;

import org.springframework.stereotype.Service;
import ru.system.thundercloud.engine.db.repository.TCLTaskRepository;
import ru.system.thundercloud.engine.db.tables.TCLTask;

import java.time.Duration;
import java.time.Instant;

/**
 *
 * @author DRakovskiy
 */

@Service
public class TCLTaskService {

    private final TCLTaskRepository tclTaskRepository;

    public TCLTaskService(TCLTaskRepository tclTaskRepository) {
        this.tclTaskRepository = tclTaskRepository;
    }

    public void insertNewTask(TCLTask tclTask) {
        tclTaskRepository.insert(tclTask.id(), tclTask.name(), tclTask.completed(), tclTask.expires_at(), tclTask.execution_id());
    }

    public TCLTask selectTaskByIdForUpdate(String id) {
        return tclTaskRepository.selectForUpdateTask(id);
    }

    public void updateTaskOnNewGetaway(String name, Boolean completed, String execution_id, Long timeDuration) {
        tclTaskRepository.updateTaskOnNewGetaway(name, completed, Instant.now().plus(Duration.ofMinutes(timeDuration)), execution_id);
    }

    public void updateTaskOnSetCompletedIfTimeOver() {
        tclTaskRepository.updateTaskOnSetCompletedIfTimeOver(Instant.now());
    }

    public void deleteCompletedTasks() {
        tclTaskRepository.deleteCompletedTasks();
    }
}
