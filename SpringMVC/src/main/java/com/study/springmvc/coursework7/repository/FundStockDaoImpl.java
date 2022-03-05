package com.study.springmvc.coursework7.repository;

import com.study.springmvc.coursework7.entity.Fund;
import com.study.springmvc.coursework7.entity.FundStock;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FundStockDaoImpl implements FundStockDao {
    private final JdbcTemplate jdbcTemplate;

    public FundStockDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FundStock> queryAll() {
        String sql = "select s.sid, s.fid, s.symbol, s.share , "
                + "f.fid as fund_fid , f.fname as fund_fname , f.createtime as fund_createtime  "
                + "from fundstock s left join fund f " + "on f.fid = s.fid order by s.sid ";
        ResultSetExtractor<List<FundStock>> resultSetExtractor =
                JdbcTemplateMapperFactory
                        .newInstance()
                        .addKeys("sid") // FundStock' sid																			// 的主鍵
                        .newResultSetExtractor(FundStock.class);

        return jdbcTemplate.query(sql, resultSetExtractor);
    }

    @Override
    public List<FundStock> queryPage(int offset) {
        if(offset < 0) {
            return queryAll();
        }
        String sql = "select s.sid, s.fid, s.symbol, s.share , "
                + "f.fid as fund_fid , f.fname as fund_fname , f.createtime as fund_createtime  "
                + "from fundstock s left join fund f " + "on f.fid = s.fid order by s.sid ";
        sql += String.format(" limit %d offset %d ", FundStockDao.LIMIT, offset);
        ResultSetExtractor<List<FundStock>> resultSetExtractor =
                JdbcTemplateMapperFactory
                        .newInstance()
                        .addKeys("sid") // FundStock' sid																			// 的主鍵
                        .newResultSetExtractor(FundStock.class);

        return jdbcTemplate.query(sql, resultSetExtractor);
    }

    @Override
    public FundStock get(Integer sid) {
        // 先找到 fundstock
        String sql = "select s.sid, s.fid, s.symbol, s.share from fundstock s where s.sid = ?";
        FundStock fundstock = jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<FundStock>(FundStock.class),
                sid);
        // 再透過 fundstock.getFid() 找到 fund
        sql = "select f.fid, f.fname, f.createtime from fund f where f.fid = ?";
        Fund fund = jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<Fund>(Fund.class),
                fundstock.getFid());
        // 注入 fund
        fundstock.setFund(fund);
        return fundstock;
    }

    @Override
    public int add(FundStock fundstock) {
        String sql = "insert into fundstock(fid, symbol, share) values(?, ?, ?)";
        int rowcount = jdbcTemplate.update(sql, fundstock.getFid(), fundstock.getSymbol(), fundstock.getShare());
        return rowcount;
    }

    @Override
    public int update(FundStock fundstock) {
        String sql = "update fundstock set fid=?, symbol=?, share=? where sid=?";
        int rowcount = jdbcTemplate.update(sql, fundstock.getFid(), fundstock.getSymbol(), fundstock.getShare(), fundstock.getSid());
        return rowcount;
    }

    @Override
    public int delete(Integer sid) {
        String sql = "delete from fundstock where sid=?";
        int rowcount = jdbcTemplate.update(sql, sid);
        return rowcount;
    }

    @Override
    public int count() {
        String sql = "select count(*) from fundstock";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
