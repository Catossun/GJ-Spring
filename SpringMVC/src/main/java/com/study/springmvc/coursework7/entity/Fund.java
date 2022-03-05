package com.study.springmvc.coursework7.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Fund {
    private Integer fid;
    private String fname;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8") // 返回時間類型
    @DateTimeFormat(pattern="yyyy-MM-dd") //接收時間類型
    private Date createtime;

    private List<FundStock> fundStocks;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public List<FundStock> getFundStocks() {
        return fundStocks;
    }

    public void setFundStocks(List<FundStock> fundStocks) {
        this.fundStocks = fundStocks;
    }
}
