package com.axelapp.jiraexcelimporter.exception;

public class ExcelException extends CustomException {

    public enum ReasonTaskSummary {
        EXCEL_PROCESSING_FAILED
    }

    private final ReasonTaskSummary reason;

    public ExcelException(String message, ReasonTaskSummary reason) {
        super(message);
        this.reason = reason;
    }

    public ExcelException(String message, Throwable ex, ReasonTaskSummary reason) {
        super(message, ex);
        this.reason = reason;
    }

    @Override
    public int getErrorCode() {
        if (this.reason == ReasonTaskSummary.EXCEL_PROCESSING_FAILED) {
            return StatusCode.EXCEL_PROCESSING_FAILED;
        }

        return StatusCode.UNHANDLED_EXCEPTION;
    }
    
}
