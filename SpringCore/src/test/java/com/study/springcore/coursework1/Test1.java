package com.study.springcore.coursework1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Test1 {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext1.xml");

        Student mary = ctx.getBean("s2", Student.class);

        Teacher t1 = ctx.getBean("t1", Teacher.class);
        Teacher t2 = ctx.getBean("t2", Teacher.class);
        Teacher[] teachers = {t1, t2};

        Set<Teacher> marysTeacher = getOnesTeachers(teachers, mary);
        System.out.println("Mary's teacher: " +
                marysTeacher.stream()
                        .map(Teacher::getName)
                        .collect(Collectors.toSet())
        );
    }

    private static Set<Teacher> getOnesTeachers(Teacher[] teachers, Student student) {
        return Arrays.stream(teachers)
                .filter(t -> t.getClazzes().stream()
                        .anyMatch(student.getClazzes()::contains))
                .collect(Collectors.toSet());
    }
}
