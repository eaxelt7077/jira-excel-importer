package com.axelapp.jiraexcelimporter.exception;

public abstract class CustomException extends RuntimeException {

    public abstract int getErrorCode();

    protected CustomException(final String message) {
    	super(message);
    }

    protected CustomException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
