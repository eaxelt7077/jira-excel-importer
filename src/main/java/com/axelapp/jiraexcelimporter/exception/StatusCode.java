package com.axelapp.jiraexcelimporter.exception;

public class StatusCode {
    
    private StatusCode() {
    }

    // Parameter error code
    private static final int PARAM_EXCEPTION = 1_000;
    public static final int EMPTY_PARAM = PARAM_EXCEPTION + 1;
    public static final int INVALID_PARAM = PARAM_EXCEPTION + 2;

    // Excel error code
    private static final int EXCEL_EXCEPTION = 1_100;
    public static final int EXCEL_PROCESSING_FAILED = EXCEL_EXCEPTION + 1;

    public static final int UNHANDLED_EXCEPTION = 9_999;
}
