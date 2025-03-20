package com.tracker.entity;

public class EntityException extends Exception {

    public EntityException(String message) {
        super(message);
    }

    public EntityException(String message, Throwable ex) {
        super(message, ex);
    }
}
