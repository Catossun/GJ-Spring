package com.study.springmvc.coursework7.repository;

import com.study.springmvc.coursework7.entity.Fund;
import com.study.springmvc.coursework7.entity.FundStock;

import java.util.List;

public interface FundStockDao {
    int LIMIT = 5;

    List<FundStock> queryAll();

    List<FundStock> queryPage(int offset);

    FundStock get(Integer fid);

    int add(FundStock fund);

    int update(FundStock fund);

    int delete(Integer fid);

    int count();
}
