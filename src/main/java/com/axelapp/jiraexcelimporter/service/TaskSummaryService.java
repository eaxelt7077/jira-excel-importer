package com.axelapp.jiraexcelimporter.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axelapp.jiraexcelimporter.exception.ExcelException;
import com.axelapp.jiraexcelimporter.exception.ParameterException;
import com.axelapp.jiraexcelimporter.exception.ExcelException.ReasonTaskSummary;
import com.axelapp.jiraexcelimporter.exception.ParameterException.ReasonParameter;
import com.axelapp.jiraexcelimporter.kafka.KafkaPublisher;
import com.axelapp.jiraexcelimporter.model.custom.TaskSummaryQueueBean;
import com.axelapp.jiraexcelimporter.model.webservice.AddTaskSummaryRequest;
import com.axelapp.jiraexcelimporter.model.webservice.EmployeeSummaryRequest;
import com.axelapp.jiraexcelimporter.model.webservice.EmployeeSummaryResponse;
import com.axelapp.jiraexcelimporter.model.webservice.base.BaseResponse;
import com.axelapp.jiraexcelimporter.service.base.BaseService;
import com.axelapp.jiraexcelimporter.utils.ExcelUtils;
import com.google.gson.Gson;

import io.micrometer.common.util.StringUtils;

@Component
public class TaskSummaryService extends BaseService {

    private static final Logger LOG = LoggerFactory.getLogger(TaskSummaryService.class);

    @Autowired Gson gson;
    @Autowired KafkaPublisher kafkaPublisher;

    public BaseResponse addTaskSummary(AddTaskSummaryRequest request) {

        if (request.getExcelBase64() == null || request.getExcelBase64().length() == 0) {
            String message = "excelBase64 is mandatory";
            throw new ParameterException(message, ReasonParameter.EMPTY_PARAM);
        }

        byte[] decodedExcel = Base64.getDecoder().decode(request.getExcelBase64());
        try {
            queueExcelData(decodedExcel);
        } catch (Exception e) {
            String message = "Error occurred when processing excel";
            throw new ExcelException(message, e, ReasonTaskSummary.EXCEL_PROCESSING_FAILED);
        }

        BaseResponse response = new BaseResponse();
        response.setStatus(0);
        response.setMessage("Success");
        return response;
    }

    private boolean isValidSummaryRow(Row row) {
        String taskType = ExcelUtils.convertCellValueToString(row.getCell(0));
        if (StringUtils.isBlank(taskType)) {
            return false;
        }

        String taskKey = ExcelUtils.convertCellValueToString(row.getCell(1));
        if (StringUtils.isBlank(taskKey)) {
            return false;
        }

        String taskDesc = ExcelUtils.convertCellValueToString(row.getCell(2));
        if (StringUtils.isBlank(taskDesc)) {
            return false;
        }

        String taskStatus = ExcelUtils.convertCellValueToString(row.getCell(3));
        if (StringUtils.isBlank(taskStatus)) {
            return false;
        }

        Long estimateInSeconds = ExcelUtils.convertCellValueToLong(row.getCell(4));
        if (null == estimateInSeconds) {
            return false;
        }

        Long timeSpentInSeconds = ExcelUtils.convertCellValueToLong(row.getCell(5));
        if (null == timeSpentInSeconds) {
            return false;
        }

        String picEmail = ExcelUtils.convertCellValueToString(row.getCell(6));
        if (StringUtils.isBlank(picEmail)) {
            return false;
        }
        Long idEmployee = daoFactory.getEmployeeDao().getIdEmployeeByEmail(picEmail);
        if (null == idEmployee) {
            return false;
        }

        Long timesReturned = ExcelUtils.convertCellValueToLong(row.getCell(7));
        if (null == timesReturned) {
            return false;
        }

        Date finishTime = ExcelUtils.convertCellValueToDate(row.getCell(8));
        return null != finishTime;
    }

    private void queueExcelData(byte[] decodedExcel) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(decodedExcel))) {

            Sheet sheet = workbook.getSheetAt(0);
            LOG.info("Reading {} rows", sheet.getPhysicalNumberOfRows());

            // Remove header
            Stream<Row> rowStream = StreamSupport.stream(sheet.spliterator(), false);
            rowStream
                .filter(row -> row.getRowNum() > 0) // remove header
                .filter(row -> isValidSummaryRow(row)) // remove invalid row (Empty cell, email that is not in DB)
                .forEach(row -> { // process all data in excel
                    String taskType = ExcelUtils.convertCellValueToString(row.getCell(0));
                    String taskKey = ExcelUtils.convertCellValueToString(row.getCell(1));
                    String taskDesc = ExcelUtils.convertCellValueToString(row.getCell(2));
                    String taskStatus = ExcelUtils.convertCellValueToString(row.getCell(3));
                    Long estimateInSeconds = ExcelUtils.convertCellValueToLong(row.getCell(4));
                    Long timeSpentInSeconds = ExcelUtils.convertCellValueToLong(row.getCell(5));
                    String picEmail = ExcelUtils.convertCellValueToString(row.getCell(6));
                    Long timesReturned = ExcelUtils.convertCellValueToLong(row.getCell(7));
                    Date finishTime = ExcelUtils.convertCellValueToDate(row.getCell(8));

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    TaskSummaryQueueBean bean = new TaskSummaryQueueBean();
                    bean.setTaskType(taskType);
                    bean.setTaskKey(taskKey);
                    bean.setTaskDesc(taskDesc);
                    bean.setTaskStatus(taskStatus);
                    bean.setEstimateInSeconds(estimateInSeconds);
                    bean.setTimeSpentInSeconds(timeSpentInSeconds);
                    bean.setPicEmail(picEmail);
                    bean.setTimesReturned(timesReturned);
                    bean.setFinishTime(sdf.format(finishTime));
                    String summaryJson = gson.toJson(bean);
                    kafkaPublisher.sendMessage(summaryJson);
                    
            });
        }
    }

    public EmployeeSummaryResponse getOverallSummary(EmployeeSummaryRequest request) {
        Long idEmployee = daoFactory.getEmployeeDao().getIdEmployeeByEmail(request.getEmail());
        EmployeeSummaryResponse response = daoFactory.getEmployeeTaskSummaryDao().getEmployeeSummary(idEmployee);
        response.setEmail(request.getEmail().toUpperCase());
        return response;
    }
    
}
