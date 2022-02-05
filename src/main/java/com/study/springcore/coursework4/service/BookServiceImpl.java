package com.study.springcore.coursework4.service;

import com.study.springcore.coursework4.dao.BookDao;
import com.study.springcore.coursework4.exception.InsufficientAmount;
import com.study.springcore.coursework4.exception.InsufficientQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    // setAutoCommit(false) -> buyOne() -> commit()
    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {InsufficientQuantity.class, InsufficientAmount.class}
    )
    @Override
    public void buyOne(Integer wid, Integer bid) throws InsufficientQuantity, InsufficientAmount {
        // Decrease book amount
        bookDao.updateStock(bid, -1);
        // Get price of book
        Integer bookPrice = bookDao.getPrice(bid);
        // Decrease money in wallet
        bookDao.updateWallet(wid, -bookPrice);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {InsufficientQuantity.class, InsufficientAmount.class}
    )
    @Override
    public void buyMany(Integer wid, Integer... bids) throws InsufficientQuantity, InsufficientAmount {
        // Do buyOne with each bid
        for (Integer bid : bids) {
            /* This method [buyOne] is not executed through Aspectj Proxy
             * because it is called directly in the same class.
             */
            buyOne(wid, bid);
        }
    }
}
