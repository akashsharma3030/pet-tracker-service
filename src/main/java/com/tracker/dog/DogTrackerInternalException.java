package com.tracker.dog;

public class DogTrackerInternalException extends Exception {
    public DogTrackerInternalException(String message) {
        super(message);
    }

    public DogTrackerInternalException(String message, Throwable ex) {
        super(message, ex);
    }
}
