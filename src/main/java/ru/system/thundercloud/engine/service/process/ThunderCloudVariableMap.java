package ru.system.thundercloud.engine.service.process;

import org.springframework.data.relational.core.sql.In;
import ru.system.thundercloud.engine.db.tables.TCLVariable;
import ru.system.thundercloud.engine.service.dto.ThunderCloudVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author DRakovskiy
 */
public class ThunderCloudVariableMap {

    private final Map<String, ThunderCloudVariable> tclVariableMap;

    public ThunderCloudVariableMap(List<TCLVariable> tclVariables) {
        Map<String, ThunderCloudVariable> tclVariableMap = HashMap.newHashMap(tclVariables.size());
        for (TCLVariable variable : tclVariables) {
            if (Objects.nonNull(variable)) {
                ThunderCloudVariable thunderCloudVariable = new ThunderCloudVariable(variable.getDeserializedValue());
                tclVariableMap.put(variable.getKey(), thunderCloudVariable);
            }
        }
        this.tclVariableMap = tclVariableMap;
    }


    public void put(String key, Object value) {
        if (tclVariableMap.containsKey(key)) {
            ThunderCloudVariable thunderCloudVariable = tclVariableMap.get(key);
            thunderCloudVariable.setValue(value);
            thunderCloudVariable.setUpdated(true);
            tclVariableMap.put(key, thunderCloudVariable);
        }
    }

    public int size() {
        return tclVariableMap.size();
    }
}
