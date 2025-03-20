package com.tracker.search;

public class NoDataFoundException extends Exception {
    public NoDataFoundException(String message) {
        super(message);
    }

    public NoDataFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
