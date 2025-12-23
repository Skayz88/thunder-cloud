package ru.system.thundercloud.engine.db.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.system.thundercloud.engine.db.repository.TCLExecutionRepository;
import ru.system.thundercloud.engine.db.tables.TCLExecution;

import java.util.Optional;

/**
 *
 * @author DRakovskiy
 */
@Service
public class TCLExecutionService {

    private final TCLExecutionRepository tclExecutionRepository;

    public TCLExecutionService(TCLExecutionRepository tclExecutionRepository) {
        this.tclExecutionRepository = tclExecutionRepository;
    }

    public void insertNewExecution(TCLExecution tclExecution) {
        tclExecutionRepository.insert(tclExecution.id(), tclExecution.name(), tclExecution.process_id());
    }

    public Optional<TCLExecution> findExecutionById(String executionId) {
        return tclExecutionRepository.findById(executionId);
    }

    public void deleteNotActualExecutions() {
        tclExecutionRepository.deleteNotActualExecutions();
    }
}
