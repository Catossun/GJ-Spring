package com.study.springcore.coursework3;

import com.study.springcore.coursework3.template.EmployeeJobDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TemplateTest3 {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext3.xml");
        EmployeeJobDao dao = ctx.getBean(EmployeeJobDao.class);

//        dao.query().forEach(emp->{
//            System.out.println(emp);
//        });

//        dao.queryJobs().forEach(job -> {
//            System.out.println(job);
//        });

//        dao.query2().forEach(emp -> {
//            System.out.println(emp);
//        });

        dao.queryJobs2().forEach(System.out::println);
    }
}
