package com.axelapp.jiraexcelimporter.dao.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axelapp.jiraexcelimporter.dao.EmployeeDao;
import com.axelapp.jiraexcelimporter.dao.EmployeeTaskSummaryDao;
@Component
public class DaoFactory {
    @Autowired private EmployeeDao employeeDao;
    @Autowired private EmployeeTaskSummaryDao employeeTaskSummaryDao;

    public EmployeeDao getEmployeeDao() {
        return this.employeeDao;
    }

    public EmployeeTaskSummaryDao getEmployeeTaskSummaryDao() {
        return this.employeeTaskSummaryDao;
    }

}
