package com.study.springcore.coursework2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;

public class TestJsonDB {
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
        JsonDB jsonDB = ctx.getBean(JsonDB.class);
        System.out.println(jsonDB.queryAll());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        boolean check = jsonDB.add(new Person("John", 0, sdf.parse("2000/1/1")));
        System.out.println(check);

        System.out.println(jsonDB.queryAll());
    }
}
