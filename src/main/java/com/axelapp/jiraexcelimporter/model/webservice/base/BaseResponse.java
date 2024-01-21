package com.axelapp.jiraexcelimporter.model.webservice.base;

public class BaseResponse {
    private int status;
    private String message;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
