package com.axelapp.jiraexcelimporter.service.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.axelapp.jiraexcelimporter.dao.base.DaoFactory;

public abstract class BaseService {
    @Autowired
    protected DaoFactory daoFactory;
}
