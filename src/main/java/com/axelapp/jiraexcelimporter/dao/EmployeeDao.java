package com.axelapp.jiraexcelimporter.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.sql2o.Connection;

import com.axelapp.jiraexcelimporter.dao.base.BaseDao;

@Component
@Transactional
public class EmployeeDao extends BaseDao {
    
    public Long getIdEmployeeByEmail(String email) {
        String query = "select idEmployee from employee where email = :email";
        try (Connection con = sql2o.open()) {
            return con.createQuery(query)
                .addParameter("email", email.toUpperCase())
                .executeScalar(Long.class);
        }
    }
}
