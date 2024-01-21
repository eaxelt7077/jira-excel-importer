package com.axelapp.jiraexcelimporter.model.webservice;

public class AddTaskSummaryRequest {
    private String excelBase64;
    
    public String getExcelBase64() {
        return this.excelBase64;
    }

    public void setExcelBase64(String excelBase64) {
        this.excelBase64 = excelBase64;
    }

}
