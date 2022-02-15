package com.study.springcore.coursework4.dao;

import com.study.springcore.coursework4.exception.InsufficientAmount;
import com.study.springcore.coursework4.exception.InsufficientQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer getPrice(Integer bid) {
        String sql = "SELECT price FROM books WHERE bid=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, bid);
    }

    @Override
    public Integer getSotckAmount(Integer bid) {
        String sql = "SELECT amount FROM stocks WHERE bid=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, bid);
    }

    @Override
    public String getWalletName(Integer wid) {
        String sql = "SELECT wname FROM wallets WHERE wid=?";
        return jdbcTemplate.queryForObject(sql, String.class, wid);
    }

    @Override
    public Integer getWalletMoney(Integer wid) {
        String sql = "SELECT money FROM wallets WHERE wid=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, wid);
    }

    @Override
    public String getBookName(Integer bid) {
        String sql = "SELECT bname FROM books WHERE bid=?";
        return jdbcTemplate.queryForObject(sql, String.class, bid);
    }

    @Override
    public Integer updateStock(Integer bid, Integer amountOffset) throws InsufficientQuantity {
        // Check current amount
        Integer curAmount = getSotckAmount(bid);
        if (curAmount <= 0) {
            throw new InsufficientQuantity(String.format("Book %d is sold out, current amount: %d", bid, curAmount));
        } else if (curAmount + amountOffset < 0) {
            throw new InsufficientQuantity(String.format("Not enough of book %d, current amount: %d", bid, curAmount));
        }

        // Update amount
        String sql = "UPDATE stocks SET amount=amount+? WHERE bid=?";
        return jdbcTemplate.update(sql, amountOffset, bid);
    }

    @Override
    public Integer updateWallet(Integer wid, Integer moneyOffset) throws InsufficientAmount {
        // Check current money
        Integer curMoney = getWalletMoney(wid);
        if (curMoney <= 0) {
            throw new InsufficientAmount(String.format("No money in wallet %d, current money: %d", wid, curMoney));
        } else if (curMoney + moneyOffset < 0) {
            throw new InsufficientAmount(String.format("Not enough money to use in wallet %d, current money: %d", wid, curMoney));
        }

        // Update money
        String sql = "UPDATE wallets SET money=money+? WHERE wid=?";
        return jdbcTemplate.update(sql, moneyOffset, wid);
    }
}
