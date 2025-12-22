package ru.system.thundercloud.engine.exceptions;

/**
 *
 * @author DRakovskiy
 */
public class ProcessNotFoundException extends RuntimeException {

    public ProcessNotFoundException(String message) {
        super(message);
    }
}
