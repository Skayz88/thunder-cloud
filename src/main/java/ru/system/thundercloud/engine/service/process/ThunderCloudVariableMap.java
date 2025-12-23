package ru.system.thundercloud.engine.service.process;

import ru.system.thundercloud.engine.db.tables.TCLVariable;
import ru.system.thundercloud.engine.service.dto.ThunderCloudVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author DRakovskiy
 */
public class ThunderCloudVariableMap {

    private final String executionId;

    private final Map<String, ThunderCloudVariable> tclVariableMap;

    public ThunderCloudVariableMap(List<TCLVariable> tclVariables, String executionId) {
        this.executionId = executionId;
        Map<String, ThunderCloudVariable> tclVariableMapNew = HashMap.newHashMap(tclVariables.size());
        for (TCLVariable variable : tclVariables) {
            if (Objects.nonNull(variable)) {
                ThunderCloudVariable thunderCloudVariable = new ThunderCloudVariable(variable.getDeserializedValue());
                tclVariableMapNew.put(variable.getKey(), thunderCloudVariable);
            }
        }
        this.tclVariableMap = tclVariableMapNew;
    }

    public ThunderCloudVariableMap(Map<String, Object> variables, String executionId) {
        this.executionId = executionId;
        Map<String, ThunderCloudVariable> tclVariableMapNew = HashMap.newHashMap(variables.size());
        variables.forEach((key, value) -> tclVariableMapNew.put(key, new ThunderCloudVariable(true, value)));
        this.tclVariableMap = tclVariableMapNew;
    }


    public String getExecutionId() {
        return executionId;
    }

    public void put(String key, Object value) {
        if (tclVariableMap.containsKey(key)) {
            ThunderCloudVariable thunderCloudVariable = tclVariableMap.get(key);
            thunderCloudVariable.setValue(value);
            thunderCloudVariable.setUpdated(true);
            tclVariableMap.put(key, thunderCloudVariable);
        } else {
            ThunderCloudVariable thunderCloudVariable = new ThunderCloudVariable(true, value);
            tclVariableMap.put(key, thunderCloudVariable);
        }
    }

    public int size() {
        return tclVariableMap.size();
    }

    public List<TCLVariable> getTCLVariables() {
        List<TCLVariable> variables = new ArrayList<>();
        tclVariableMap.forEach((key, value) -> {
            if (value.isUpdated()) {
                TCLVariable tclVariable = thunderCloudVariableToTCLVariable(key,value,executionId);
                variables.add(tclVariable);
            }
        });
        return variables;
    }

    public Object get(String key) {
        if (tclVariableMap.containsKey(key)) {
            ThunderCloudVariable thunderCloudVariable = tclVariableMap.get(key);
            return thunderCloudVariable.getValue();
        }
        return null;
    }

    @Override
    public String toString() {
        return tclVariableMap.toString();
    }

    private TCLVariable thunderCloudVariableToTCLVariable(String key, ThunderCloudVariable thunderCloudVariable, String executionId) {
        TCLVariable tclVariable = new TCLVariable();
        tclVariable.setId(executionId + "_" + key);
        tclVariable.setKey(key);
        tclVariable.setDeserializedValue(thunderCloudVariable.getValue());
        tclVariable.setExecutionId(executionId);
        return tclVariable;
    }
}
