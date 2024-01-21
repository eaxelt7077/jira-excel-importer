package com.axelapp.jiraexcelimporter.controller.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.axelapp.jiraexcelimporter.exception.CustomException;
import com.axelapp.jiraexcelimporter.exception.StatusCode;
import com.axelapp.jiraexcelimporter.model.webservice.base.BaseResponse;

public class BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
    
    @ExceptionHandler
    protected BaseResponse handleCustomException(CustomException e) {
        LOG.error("Throwing custom exception", e);

        BaseResponse response = new BaseResponse();
        response.setStatus(e.getErrorCode());
        response.setMessage(e.getMessage());
        return response;
    }

    @ExceptionHandler
    protected BaseResponse handleOtherException(Exception e) {
        LOG.error("Throwing unhandled exception", e);

        BaseResponse response = new BaseResponse();
        response.setStatus(StatusCode.UNHANDLED_EXCEPTION);
        response.setMessage("Internal server error");
        return response;
    }
}
