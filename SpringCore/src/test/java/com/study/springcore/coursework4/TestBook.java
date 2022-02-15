package com.study.springcore.coursework4;

import com.study.springcore.coursework4.controller.BookController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBook {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext4.xml");
        BookController controller = ctx.getBean(BookController.class);

        Integer wid = 1;
        // Case 1
        Integer bid = 1;
        controller.buyBook(wid, bid);

        // Case 2
        controller.buyBooks(wid, 1, 1, 2, 2, 2);
    }
}
