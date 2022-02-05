package com.study.springcore.coursework3;

import com.study.springcore.coursework3.template.EmployeeDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class TemplateTest1 {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext3.xml");
        EmployeeDao dao = ctx.getBean("employeeDao", EmployeeDao.class);
        List<Map<String, Object>> emps = dao.queryAll();
        System.out.println(emps);
        System.out.println(emps.stream()
                .filter(emp -> emp.get("eid").equals(2))
                .findFirst()
                .get()
                .get("ename")
        );

        System.out.println(dao.queryAllEmps());
        System.out.println(dao.queryAllEmps2());
    }
}
