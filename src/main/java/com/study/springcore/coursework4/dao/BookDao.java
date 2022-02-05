package com.study.springcore.coursework4.dao;

import com.study.springcore.coursework4.exception.InsufficientAmount;
import com.study.springcore.coursework4.exception.InsufficientQuantity;

public interface BookDao {
    Integer getPrice(Integer bid);

    Integer getSotckAmount(Integer bid);

    String getWalletName(Integer wid);

    Integer getWalletMoney(Integer wid);

    String getBookName(Integer bid);

    Integer updateStock(Integer bid, Integer amountOffset) throws InsufficientQuantity;

    Integer updateWallet(Integer wid, Integer moneyOffset) throws InsufficientAmount;
}
