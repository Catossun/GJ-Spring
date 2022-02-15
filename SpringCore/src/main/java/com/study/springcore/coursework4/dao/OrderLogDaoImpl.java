package com.study.springcore.coursework4.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderLogDaoImpl implements OrderLogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int log(Integer wid, Integer bid, Integer bookPrice, Integer amount) {
        String sql = "INSERT INTO order_log(wid, bid, book_price, amount) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql, wid, bid, bookPrice, amount);
    }
}
