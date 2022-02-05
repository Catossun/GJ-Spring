package com.study.springcore.coursework3.template;

import com.study.springcore.coursework3.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Map<String, Object>> queryAll() {
        String sql = "SELECT eid, ename, age, createtime FROM emp";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Emp> queryAllEmps() {
        String sql = "SELECT eid, ename, age, createtime FROM emp";
        List<Emp> emps = jdbcTemplate.query(sql, (resultSet, i) -> {
            Emp emp = new Emp();
            emp.setEid(resultSet.getInt("eid"));
            emp.setEname(resultSet.getString("ename"));
            emp.setAge(resultSet.getInt("age"));
            emp.setCreateTime(resultSet.getTimestamp("createtime"));
            return emp;
        });
        return emps;
    }

    public List<Emp> queryAllEmps2() {
        String sql = "SELECT eid, ename, age, createtime FROM emp";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Emp.class));
    }

    public int add(String name, Integer age) {
        String sql = "INSERT INTO emp(ename, age) VALUES(?, ?)";
        int rowCount = jdbcTemplate.update(sql, name, age);
        return rowCount;
    }

    public int add2(String name, Integer age) {
        String sql = "INSERT INTO emp(ename, age) VALUES(:ename, :age)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("ename", name)
                .addValue("age", age);
        int rowCount = namedParameterJdbcTemplate.update(sql, params);
        return rowCount;
    }

    public int[] add(List<Object[]> rows) {
        String sql = "INSERT INTO emp(ename, age) VALUES(?, ?)";
        return jdbcTemplate.batchUpdate(sql, rows);
    }

    public int[] add2(List<Emp> emps) {
        String sql = "INSERT INTO emp(ename, age) VALUES(?, ?)";
        BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, emps.get(i).getEname());
                preparedStatement.setInt(2, emps.get(i).getAge());
            }

            @Override
            public int getBatchSize() {
                return emps.size();
            }
        };
        return jdbcTemplate.batchUpdate(sql, setter);
    }

    public int updateById(Integer id, String name, Integer age) {
        String sql = "UPDATE emp SET ename=?, age=? WHERE eid=?";
        return jdbcTemplate.update(sql, name, age, id);
    }

    public int removeById(Integer id) {
        String sql = "DELETE FROM emp WHERE eid=?";
        return jdbcTemplate.update(sql, id);
    }
}
