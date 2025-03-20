package com.tracker.cat;

public class CatTrackerInternalException extends Exception {
    public CatTrackerInternalException(String message) {
        super(message);
    }

    public CatTrackerInternalException(String message, Throwable ex) {
        super(message, ex);
    }
}
