package com.axelapp.jiraexcelimporter.model.custom;

public class TaskSummaryQueueBean {
    private String taskType;
    private String taskKey;
    private String taskDesc;
    private String taskStatus;
    private Long estimateInSeconds;
    private Long timeSpentInSeconds;
    private String picEmail;
    private Long timesReturned;
    private String finishTime; // yyyy-MM-dd HH:mm:ss

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

    public String getTaskDesc() {
        return this.taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskStatus() {
        return this.taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Long getEstimateInSeconds() {
        return this.estimateInSeconds;
    }

    public void setEstimateInSeconds(Long estimateInSeconds) {
        this.estimateInSeconds = estimateInSeconds;
    }

    public Long getTimeSpentInSeconds() {
        return this.timeSpentInSeconds;
    }

    public void setTimeSpentInSeconds(Long timeSpentInSeconds) {
        this.timeSpentInSeconds = timeSpentInSeconds;
    }

    public String getPicEmail() {
        return this.picEmail;
    }

    public void setPicEmail(String picEmail) {
        this.picEmail = picEmail;
    }

    public Long getTimesReturned() {
        return this.timesReturned;
    }

    public void setTimesReturned(Long timesReturned) {
        this.timesReturned = timesReturned;
    }

    public String getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

}
