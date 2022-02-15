package com.study.springcore.coursework4.controller;

import com.study.springcore.coursework4.exception.InsufficientAmount;
import com.study.springcore.coursework4.exception.InsufficientQuantity;
import com.study.springcore.coursework4.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    public void buyBook(Integer wid, Integer bid) {
        try {
            bookService.buyOne(wid, bid);
        } catch (InsufficientQuantity insufficientQuantity) {
            insufficientQuantity.printStackTrace();
        } catch (InsufficientAmount insufficientAmount) {
            insufficientAmount.printStackTrace();
        }
        System.out.println("Buy one OK!");
    }

    public void buyBooks(Integer wid, Integer... bids) {
        try {
            bookService.buyMany(wid, bids);
        } catch (InsufficientQuantity insufficientQuantity) {
            insufficientQuantity.printStackTrace();
        } catch (InsufficientAmount insufficientAmount) {
            insufficientAmount.printStackTrace();
        }
        System.out.println("Buy many OK!");
    }
}
