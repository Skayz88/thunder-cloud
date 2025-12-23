package ru.system.thundercloud.engine.db.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.system.thundercloud.engine.db.repository.TCLVariableRepository;
import ru.system.thundercloud.engine.db.tables.TCLVariable;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author DRakovskiy
 */
@Service
public class TCLVariableService {

    private final TCLVariableRepository tclVariableRepository;

    public TCLVariableService(TCLVariableRepository tclVariableRepository) {
        this.tclVariableRepository = tclVariableRepository;
    }

    public void saveVariable(TCLVariable variable) throws IOException {
        variable.encodeValue(variable.getDeserializedValue()); // Сериализуем объект
        tclVariableRepository.save(variable);
    }

    public Optional<TCLVariable> getVariable(String id) throws Exception {
        Optional<TCLVariable> optVariable = tclVariableRepository.findById(id);
        if (optVariable.isPresent()) {
            TCLVariable variable = optVariable.get();
            variable.decodeValue();
            return Optional.of(variable);
        }
        return Optional.empty();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveVariables(List<TCLVariable> variables) throws IOException {
        for (TCLVariable variable : variables) {
            tclVariableRepository.insert(
                    variable.getId(),
                    variable.getKey(),
                    variable.getValue(),
                    variable.getExecutionId()
            );
        }
    }
}
