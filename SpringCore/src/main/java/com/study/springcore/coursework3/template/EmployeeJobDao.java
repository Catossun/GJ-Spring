package com.study.springcore.coursework3.template;

import com.study.springcore.coursework3.entity.Emp;
import com.study.springcore.coursework3.entity.Job;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeJobDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Emp> query() {
        String sql = "SELECT eid, ename, age, createtime FROM emp";
        List<Emp> emps = jdbcTemplate.query(sql, (resultSet, i) -> {
            Emp emp = new Emp();
            emp.setEid(resultSet.getInt("eid"));
            emp.setEname(resultSet.getString("ename"));
            emp.setAge(resultSet.getInt("age"));
            emp.setCreateTime(resultSet.getTimestamp("createtime"));

            String sql2 = "SELECT jid, jname, eid FROM jobs WHERE eid=?";
            List<Job> jobs = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<>(Job.class), emp.getEid());
            emp.setJobs(jobs);

            return emp;
        });

        return emps;
    }

    public List<Job> queryJobs() {
        String sql = "SELECT jid, jname, eid FROM jobs";
        List<Job> jobs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Job.class));

        jobs.forEach(job -> {
            String sql2 = "SELECT eid, ename, age, createtime FROM emp WHERE eid=?";
            List<Emp> emp = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<>(Emp.class), job.getEid());
            if (!emp.isEmpty()) {
                job.setEmp(emp.get(0));
            }
        });

        return jobs;
    }

    public List<Emp> query2() {
        String sql = "SELECT e.eid, e.ename, e.age, e.createtime,\n" +
                "       j.jid AS job_jid, j.jname AS job_jname, j.eid AS job_eid\n" +
                "FROM emp e LEFT OUTER JOIN jobs j on e.eid = j.eid;";
        ResultSetExtractor<List<Emp>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
                .addKeys("eid")
                .newResultSetExtractor(Emp.class);

        return jdbcTemplate.query(sql, resultSetExtractor);
    }

    public List<Job> queryJobs2() {
        String sql = "SELECT e.eid AS emp_eid, e.ename AS emp_ename, e.age AS emp_age, e.createtime AS emp_createtime,\n" +
                "       j.jid, j.jname, j.eid\n" +
                "FROM emp e RIGHT OUTER JOIN jobs j on e.eid = j.eid;";
        ResultSetExtractor<List<Job>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
                .addKeys("jid")
                .newResultSetExtractor(Job.class);

        return jdbcTemplate.query(sql, resultSetExtractor);
    }
}
