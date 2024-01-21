package com.axelapp.jiraexcelimporter.exception;

public class ParameterException extends CustomException {

    public enum ReasonParameter {
        EMPTY_PARAM,
        INVALID_PARAM
    }

    private final ReasonParameter reason;

    public ParameterException(String message, ReasonParameter reason) {
        super(message);
        this.reason = reason;
    }

    public ParameterException(String message, Throwable ex, ReasonParameter reason) {
        super(message, ex);
        this.reason = reason;
    }

    @Override
    public int getErrorCode() {
        if (this.reason == ReasonParameter.EMPTY_PARAM) {
            return StatusCode.EMPTY_PARAM;
        }
        if (this.reason == ReasonParameter.INVALID_PARAM) {
            return StatusCode.INVALID_PARAM;
        }

        return StatusCode.UNHANDLED_EXCEPTION;
    }
    
}
