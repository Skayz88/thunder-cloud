package ru.system.thundercloud.engine.exceptions;

/**
 *
 * @author DRakovskiy
 */
public class ExecutionNotFoundById extends RuntimeException {

    public ExecutionNotFoundById(String message) {
        super(message);
    }
}
