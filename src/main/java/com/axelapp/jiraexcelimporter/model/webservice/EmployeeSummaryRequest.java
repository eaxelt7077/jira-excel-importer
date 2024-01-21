package com.axelapp.jiraexcelimporter.model.webservice;

public class EmployeeSummaryRequest {
    private String email;
    private Integer month;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMonth() {
        return this.month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

}
