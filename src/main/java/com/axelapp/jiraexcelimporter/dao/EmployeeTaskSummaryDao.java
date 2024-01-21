package com.axelapp.jiraexcelimporter.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.sql2o.Connection;

import com.axelapp.jiraexcelimporter.dao.base.BaseDao;
import com.axelapp.jiraexcelimporter.model.database.EmployeeTaskSummary;
import com.axelapp.jiraexcelimporter.model.webservice.EmployeeSummaryResponse;

@Component
@Transactional
public class EmployeeTaskSummaryDao extends BaseDao{
    
    public void insertEmployeeTaskSummary(EmployeeTaskSummary summary) {
        StringBuilder query = new StringBuilder();
        query
            .append("insert into employeeTaskSummary(taskType, taskKey, taskDescription, taskStatus, finishTime, timeSpentSeconds, ")
            .append("timeEstimationSeconds, timesReturned, idEmployee, createdBy, createdAt) ")
            .append("select :taskType , :taskKey , :taskDescription , :taskStatus , :finishTime , :timeSpentSeconds , ")
            .append(":timeEstimationSeconds , :timesReturned , :idEmployee , :createdBy , :createdAt ")
            .append("where not exists ( ")
                .append("select 1 from employeeTaskSummary ")
                .append("where taskKey = :taskKey ")
            .append(") ");

        try (Connection con = sql2o.open()) {
            con.createQuery(query.toString())
                .addParameter("taskType", summary.getTaskType())
                .addParameter("taskKey", summary.getTaskKey())
                .addParameter("taskDescription", summary.getTaskDescription())
                .addParameter("taskStatus", summary.getTaskStatus())
                .addParameter("finishTime", summary.getFinishTime())
                .addParameter("timeSpentSeconds", summary.getTimeSpentSeconds())
                .addParameter("timeEstimationSeconds", summary.getTimeEstimationSeconds())
                .addParameter("timesReturned", summary.getTimesReturned())
                .addParameter("idEmployee", summary.getIdEmployee())
                .addParameter("createdBy", summary.getCreatedBy())
                .addParameter("createdAt", summary.getCreatedAt())
                .executeUpdate()
                .getResult();
                
        }
    }

    public EmployeeSummaryResponse getEmployeeSummary(Long idEmployee) {
        StringBuilder query = new StringBuilder();
        query
            .append("select (sum(timeSpentSeconds) / sum(timeEstimationSeconds)) as \"productivty\", ")
            .append("sum(CAST(timesReturned AS double precision))/count(timesReturned) as \"mistakesPerTask\" ")
            .append("from employeeTaskSummary ")
            .append("where idEmployee = :idEmployee ");

        try (Connection con = sql2o.open()) {
            return con.createQuery(query.toString())
                .addParameter("idEmployee", idEmployee)
                .executeAndFetchFirst(EmployeeSummaryResponse.class);

        }
    }
}
