package ru.system.thundercloud.engine.db.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.system.thundercloud.engine.db.repository.TCLVariableRepository;
import ru.system.thundercloud.engine.db.tables.TCLVariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author DRakovskiy
 */
@Service
public class TCLVariableService {

    private static final String SQL_INSERT_MERGE = "INSERT INTO tcl_variable (id, key, value, execution_id) "
            + "VALUES %s "
            + "ON CONFLICT (id) DO UPDATE "
            + "SET value = EXCLUDED.value";
    private static final String FORMAT_FOR_INSERT_VARIABLES = "( '%s', '%s', '%s', '%s' )";

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TCLVariableService.class);

    private final TCLVariableRepository tclVariableRepository;
    private final JdbcTemplate jdbcTemplate;

    public TCLVariableService(TCLVariableRepository tclVariableRepository, JdbcTemplate jdbcTemplate) {
        this.tclVariableRepository = tclVariableRepository;
        this.jdbcTemplate = jdbcTemplate;
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

    public List<TCLVariable> getTCLVariablesForThisExecution(String executionId) {
        List<TCLVariable> tclVariables = tclVariableRepository.findByExecutionId(executionId);
        List<TCLVariable> tclVariableList = new ArrayList<>(tclVariables.size());
        tclVariables.forEach(tclVariable -> {
            try {
                tclVariable.decodeValue();
                tclVariableList.add(tclVariable);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });
        return tclVariableList;
    }

    public void deleteVariableNotActualExecutions() {
        tclVariableRepository.deleteVariableNotActualExecutions();
    }


    public void saveVariables(List<TCLVariable> variables) throws IOException {
        StringBuilder sqlBuilder = new StringBuilder();
        String formatString = FORMAT_FOR_INSERT_VARIABLES;
        for (TCLVariable variable : variables) {
            variable.encodeValue(variable.getDeserializedValue());
            sqlBuilder.append(String.format(formatString, variable.getId(), variable.getKey(), variable.getValue(), variable.getExecutionId()));
            formatString = ", " + FORMAT_FOR_INSERT_VARIABLES;
        }
        if (!sqlBuilder.isEmpty()) {
            String sqlForExecute = String.format(SQL_INSERT_MERGE, sqlBuilder);
            jdbcTemplate.execute(sqlForExecute);
        }
    }
}
