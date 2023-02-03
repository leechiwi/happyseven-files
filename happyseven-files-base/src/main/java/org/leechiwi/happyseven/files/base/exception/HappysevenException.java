package org.leechiwi.happyseven.files.base.exception;

public class HappysevenException extends RuntimeException {
    public HappysevenException(String message) {
        super(message);
    }

    public HappysevenException(String message, Throwable e) {
        super(message, e);
    }
}
