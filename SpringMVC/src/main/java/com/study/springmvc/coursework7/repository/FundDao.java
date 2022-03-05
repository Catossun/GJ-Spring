package com.study.springmvc.coursework7.repository;

import com.study.springmvc.coursework7.entity.Fund;

import java.util.List;

public interface FundDao {
    int LIMIT = 5;

    List<Fund> queryAll();

    List<Fund> queryPage(int offset);

    Fund get(Integer fid);

    int add(Fund fund);

    int update(Fund fund);

    int delete(Integer fid);

    int count();
}
