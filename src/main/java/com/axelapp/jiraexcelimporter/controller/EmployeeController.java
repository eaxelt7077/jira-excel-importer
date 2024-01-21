package com.axelapp.jiraexcelimporter.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axelapp.jiraexcelimporter.controller.base.BaseController;
import com.axelapp.jiraexcelimporter.model.webservice.EmployeeSummaryRequest;
import com.axelapp.jiraexcelimporter.model.webservice.EmployeeSummaryResponse;
import com.axelapp.jiraexcelimporter.service.TaskSummaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

    @Autowired private TaskSummaryService taskSummaryService;
    
    @PostMapping("/overallSummary")
    public EmployeeSummaryResponse getOverallSummary(@RequestBody EmployeeSummaryRequest request) {
        return taskSummaryService.getOverallSummary(request);
    }
    
}
