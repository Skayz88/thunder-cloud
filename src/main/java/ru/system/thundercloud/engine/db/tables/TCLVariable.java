package ru.system.thundercloud.engine.db.tables;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;
import ru.system.thundercloud.engine.util.SerializationUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;

/**
 *
 * @author DRakovskiy
 */

@Table("tcl_variable")
public class TCLVariable implements Serializable {

    @Id
    private String id;
    private String key;
    private String value;
    @Transient
    private Object deserializedValue;
    private String executionId;

    public Object getDeserializedValue() {
        return deserializedValue;
    }

    public void setDeserializedValue(Object deserializedValue) {
        this.deserializedValue = deserializedValue;
    }

    public void encodeValue(Object obj) throws IOException {
        byte[] serializedBytes = SerializationUtil.serialize(obj);
        this.value = Base64.getEncoder().encodeToString(serializedBytes);
    }

    public void decodeValue() throws IOException, ClassNotFoundException {
        byte[] decodedBytes = Base64.getDecoder().decode(value);
        this.deserializedValue = SerializationUtil.deserialize(decodedBytes);
    }

    public TCLVariable() {
    }

    public TCLVariable(String id, String key, String value, Object deserializedValue, String executionId) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.deserializedValue = deserializedValue;
        this.executionId = executionId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getExecutionId() {
        return executionId;
    }
}
