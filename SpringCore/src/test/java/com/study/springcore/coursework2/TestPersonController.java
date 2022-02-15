package com.study.springcore.coursework2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPersonController {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
        PersonController personController = ctx.getBean("personController", PersonController.class);

        System.out.println(personController.getPersonByName("Randy"));
        System.out.println(personController.getPersonByName("Tom"));
    }
}
