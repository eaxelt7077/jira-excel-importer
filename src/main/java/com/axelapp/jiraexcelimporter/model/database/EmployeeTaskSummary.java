package com.axelapp.jiraexcelimporter.model.database;

import java.util.Date;

public class EmployeeTaskSummary {
    private Long idTaskSummary;
    private String taskType;
    private String taskKey;
    private String taskDescription;
    private String taskStatus;
    private Date finishTime;
    private Long timeSpentSeconds;
    private Long timeEstimationSeconds;
    private Integer timesReturned;
    private Long idEmployee;
    private String createdBy;
    private Date createdAt;
    private String updatedBy;
    private Date updatedAt;

    public Long getIdTaskSummary() {
        return this.idTaskSummary;
    }

    public void setIdTaskSummary(Long idTaskSummary) {
        this.idTaskSummary = idTaskSummary;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskKey() {
        return this.taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskStatus() {
        return this.taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Date getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Long getTimeSpentSeconds() {
        return this.timeSpentSeconds;
    }

    public void setTimeSpentSeconds(Long timeSpentSeconds) {
        this.timeSpentSeconds = timeSpentSeconds;
    }

    public Long getTimeEstimationSeconds() {
        return this.timeEstimationSeconds;
    }

    public void setTimeEstimationSeconds(Long timeEstimationSeconds) {
        this.timeEstimationSeconds = timeEstimationSeconds;
    }

    public Integer getTimesReturned() {
        return this.timesReturned;
    }

    public void setTimesReturned(Integer timesReturned) {
        this.timesReturned = timesReturned;
    }

    public Long getIdEmployee() {
        return this.idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }   

}
