package com.axelapp.jiraexcelimporter.model.webservice;

public class EmployeeSummaryResponse {
    private String email;
    private String productivty;
    private String mistakesPerTask;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductivty() {
        return this.productivty;
    }

    public void setProductivty(String productivty) {
        this.productivty = productivty;
    }

    public String getMistakesPerTask() {
        return this.mistakesPerTask;
    }

    public void setMistakesPerTask(String mistakesPerTask) {
        this.mistakesPerTask = mistakesPerTask;
    }

}
