package com.study.springcore.coursework4.service;

import com.study.springcore.coursework4.exception.InsufficientAmount;
import com.study.springcore.coursework4.exception.InsufficientQuantity;

public interface BookService {
    void buyOne(Integer wid, Integer bid) throws InsufficientQuantity, InsufficientAmount;

    void buyMany(Integer wid, Integer... bids) throws InsufficientQuantity, InsufficientAmount;
}
