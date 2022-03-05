package com.study.springmvc.coursework7.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FundStock {
    private Integer sid;
    @NotNull(message = "{stock.fund.empty}")
    private Integer fid;
    @NotEmpty(message = "{stock.symbol.empty}")
    private String symbol;
    @Min(value = 1, message = "{stock.share.min}")
    @NotNull(message = "{stock.share.empty}")
    private Integer share;
    private Fund fund;

    @Override
    public String toString() {
        return "FundStock{" +
                "sid=" + sid +
                ", fid=" + fid +
                ", symbol='" + symbol + '\'' +
                ", share=" + share +
                ", fund=" + fund +
                '}';
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getShare() {
        return share;
    }

    public void setShare(Integer share) {
        this.share = share;
    }

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }
}

