package com.tracker.dog;

public class DogTrackerException extends Exception {
    public DogTrackerException(String message) {
        super(message);
    }

    public DogTrackerException(String message, Throwable ex) {
        super(message, ex);
    }
}
