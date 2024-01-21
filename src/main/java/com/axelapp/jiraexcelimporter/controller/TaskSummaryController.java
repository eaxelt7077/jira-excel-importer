package com.axelapp.jiraexcelimporter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axelapp.jiraexcelimporter.controller.base.BaseController;
import com.axelapp.jiraexcelimporter.model.webservice.AddTaskSummaryRequest;
import com.axelapp.jiraexcelimporter.model.webservice.base.BaseResponse;
import com.axelapp.jiraexcelimporter.service.TaskSummaryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/taskSummary")
public class TaskSummaryController extends BaseController {

    @Autowired private TaskSummaryService taskSummaryService;
    
    @PostMapping("/addFromExcel")
    public BaseResponse addTaskSummary(@RequestBody AddTaskSummaryRequest request) {
        return taskSummaryService.addTaskSummary(request);
    }
    
}
