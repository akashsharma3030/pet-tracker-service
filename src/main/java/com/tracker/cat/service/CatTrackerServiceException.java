package com.tracker.cat.service;

public class CatTrackerServiceException extends Exception {

    public CatTrackerServiceException(String message) {
        super(message);
    }

    public CatTrackerServiceException(String message, Throwable ex) {
        super(message, ex);
    }

}
