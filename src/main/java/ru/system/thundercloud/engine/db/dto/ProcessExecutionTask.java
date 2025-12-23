package ru.system.thundercloud.engine.db.dto;

/**
 *
 * @author DRakovskiy
 */
public record ProcessExecutionTask (
    String processId,
    String processName,
    String executionId,
    String executionName,
    String taskId,
    String taskName,
    boolean completed
) { }
