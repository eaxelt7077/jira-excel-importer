package com.axelapp.jiraexcelimporter.dao.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.sql2o.Sql2o;

@Component
@Transactional
public abstract class BaseDao {
    @Autowired
    protected Sql2o sql2o;
}
