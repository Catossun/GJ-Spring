package com.study.springmvc.coursework7.controller;

import com.study.springmvc.coursework7.entity.Fund;
import com.study.springmvc.coursework7.repository.FundDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/coursework7/fund")
public class FundController {
    private int curPageNumber = -1;

    @Autowired
    private FundDao fundDao;

    @GetMapping("/")
    public List<Fund> index() {
        List<Fund> funds = fundDao.queryAll();
        funds = funds.stream().peek(fund -> {
            if (fund.getFundStocks().stream().anyMatch(fundStock -> Objects.isNull(fundStock.getFid())))
                fund.setFundStocks(null);
        }).collect(Collectors.toList());
        return funds;
    }

    @GetMapping("/page/{pageNumber}")
    public List<Fund> page(@PathVariable int pageNumber){
        curPageNumber = pageNumber;
        int offset = (pageNumber -1) * FundDao.LIMIT;
        return fundDao.queryPage(offset);
    }

    @GetMapping("/page/pagecount")
    public Integer pageCount(){
        return fundDao.count() / FundDao.LIMIT + 1;
    }

    @GetMapping("/{fid}")
    public Fund get(@PathVariable("fid") Integer fid) {
        return fundDao.get(fid);
    }

    @PostMapping("/")
    public int add(@RequestBody Fund fund) {
        return fundDao.add(fund);
    }

    @PutMapping("/")
    public int update(@RequestBody Fund fund) {
        return fundDao.update(fund);
    }

    @DeleteMapping("/{fid}")
    public int delete(@PathVariable("fid") Integer fid) {
        return fundDao.delete(fid);
    }
}
