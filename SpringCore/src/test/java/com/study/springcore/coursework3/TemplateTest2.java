package com.study.springcore.coursework3;

import com.study.springcore.coursework3.template.EmployeeDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TemplateTest2 {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext3.xml");
        EmployeeDao dao = ctx.getBean(EmployeeDao.class);
//        int rowCount = dao.add("test1", 19);
//        System.out.println("Add: " + rowCount);

//        int rowCount = dao.add("test2", 29);
//        System.out.println("Add2: " + rowCount);

//        List<Object[]> rows = new ArrayList<>();
//        rows.add(new Object[]{"Jo", 12});
//        rows.add(new Object[]{"Mark", 22});
//        rows.add(new Object[]{"Hank", 42});
//        int[] rowCount = dao.add(rows);
//        System.out.println(Arrays.toString(rowCount));

//        List<Employee> emps = new ArrayList<>();
//        emps.add(new Employee("Bb", 39));
//        emps.add(new Employee("Ace", 28));
//        int[] rowCount = dao.add2(emps);
//        System.out.println(Arrays.toString(rowCount));

//        int rowCount = dao.updateById(1, "Walk", 3);
//        System.out.println("Update: " + rowCount);

        int rowCount = dao.removeById(1);
        System.out.println("Remove: " + rowCount);
    }
}
