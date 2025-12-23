/*
 * VTB Group. Do not reproduce without permission in writing.
 *
 * Copyright (c) 2025 VTB Group. All rights reserved.
 */

package ru.system.thundercloud.engine.service.dto;

/**
 *
 * @author DRakovskiy
 */
public class ThunderCloudVariable {

    private boolean isUpdated;
    private Object value;

    public ThunderCloudVariable(Object value) {
        this.isUpdated = false;
        this.value = value;
    }

    public ThunderCloudVariable(boolean isNew, Object value) {
        this.isUpdated = isNew;
        this.value = value;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
