package com.tracker.cat;

public class CatTrackerException extends Exception {

    public CatTrackerException(String message) {
        super(message);
    }

    public CatTrackerException(String message, Throwable ex) {
        super(message, ex);
    }

}
