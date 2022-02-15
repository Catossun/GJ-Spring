package com.study.springcore.coursework3.entity;

public class Job {
    private Integer jid;
    private String jname;
    private Integer eid;

    private Emp emp;

    @Override
    public String toString() {
        return "Job{" +
                "jid=" + jid +
                ", jname='" + jname + '\'' +
                ", eid=" + eid +
                ", emp=" + (emp == null ? null : emp.getEname()) +
                '}';
    }

    public Integer getJid() {
        return jid;
    }

    public void setJid(Integer jid) {
        this.jid = jid;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }
}
