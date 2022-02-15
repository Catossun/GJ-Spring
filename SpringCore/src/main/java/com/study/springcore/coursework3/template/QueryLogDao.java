package com.study.springcore.coursework3.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QueryLogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(String methodName) {
        String sql = "INSERT INTO query_log(method_name) value ('" + methodName + "')";
        jdbcTemplate.execute(sql);
        System.out.println("Query Logged!");
    }
}
