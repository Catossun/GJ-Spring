package com.study.springcore.coursework3.entity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Emp {
    private Integer eid;
    private String ename;
    private Integer age;
    private Date createTime;

    private List<Job> jobs;

    public Emp() {
    }

    public Emp(String ename, Integer age) {
        this.ename = ename;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", ename='" + ename + '\'' +
                ", age=" + age +
                ", createTime=" + createTime +
                ", jobs=" + jobs.stream().map(Job::getJname).collect(Collectors.toList()) +
                '}';
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
