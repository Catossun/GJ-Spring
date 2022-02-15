package com.study.springcore.coursework4.dao;

public interface OrderLogDao {
    int log(Integer wid, Integer bid, Integer bookPrice, Integer amount);
}
