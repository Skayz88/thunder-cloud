package ru.system.thundercloud.engine.db.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.system.thundercloud.engine.db.repository.TCLTaskRepository;
import ru.system.thundercloud.engine.db.tables.TCLTask;

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
        tclTaskRepository.insert(tclTask.id(), tclTask.name(), tclTask.completed(), tclTask.execution_id());
    }

    public void updateTaskOnNewGetaway(String name, Boolean completed, String execution_id) {
        tclTaskRepository.updateTaskOnNewGetaway(name, completed, execution_id);
    }

    public void deleteCompletedTasks() {
        tclTaskRepository.deleteCompletedTasks();
    }
}
