package com.study.springcore.coursework2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PersonSystem {
    private final ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext8.xml");
    private final PersonController personController = ctx.getBean("personController", PersonController.class);
    private boolean stop;

    public static void main(String[] args) {
        new PersonSystem().start();
    }

    private void menu() {
        System.out.println("=========================================");
        System.out.println("1. 建立 Person 資料");
        System.out.println("2. 取得 Person 全部資料");
        // 作業 2：
        System.out.println("3. 根據姓名取得 Person");
        System.out.println("4. 取得今天生日的 Person");
        System.out.println("5. 取得某一歲數以上的 Person");
        System.out.println("6. 根據姓名來修改Person的生日");
        System.out.println("7. 根據姓名來刪除Person");
        System.out.println("0. 離開");
        System.out.println("=========================================");
        System.out.print("請選擇： ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 0:
                System.out.println("離開系統");
                stop = true;
                break;
            case 1:
                createPerson();
                break;
            case 2:
                printPersons();
                break;
            case 3:
                getPersonByName();
                break;
            case 4:
                getTodayBirthPerson();
                break;
            case 5:
                getPeopleGreaterThenAge();
                break;
            case 6:
                editPersonBirthdayByName();
                break;
            case 7:
                removePersonByName();
                break;
        }
    }

    private void createPerson() {
        System.out.print("請輸入姓名 生日年 月 日： ");
        // Ex: Jack 1999 4 5
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        int yyyy = sc.nextInt();
        int mm = sc.nextInt();
        int dd = sc.nextInt();
        personController.addPerson(name, yyyy, mm, dd);
    }

    private void printPersons() {
        List<Person> people = personController.getAllPerson();
        personController.printPersonInfo(people);
    }

    private void getPersonByName() {
        System.out.print("請輸入姓名：");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        Person person = personController.getPersonByName(name);
        if (person == null) {
            System.out.println("查無此人");
            return;
        }
        personController.printPersonInfo(person);
    }

    private void getTodayBirthPerson() {
        System.out.print("請輸入今天的 月 日： ");
        Scanner sc = new Scanner(System.in);
        int mm = sc.nextInt();
        int dd = sc.nextInt();
        List<Person> people = personController.getAllPerson();
        List<Person> birthPeople = people.stream()
                .filter(p -> {
                    Calendar birthday = Calendar.getInstance();
                    birthday.setTime(p.getBirth());
                    return mm - 1 == birthday.get(Calendar.MONTH) &&
                            dd == birthday.get(Calendar.DAY_OF_MONTH);
                })
                .collect(Collectors.toList());
        personController.printPersonInfo(birthPeople);
    }

    private void getPeopleGreaterThenAge() {
        System.out.print("請輸入年齡：");
        Scanner sc = new Scanner(System.in);
        int age = sc.nextInt();
        List<Person> people = personController.getAllPerson();
        List<Person> result = people.stream()
                .filter(p -> p.getAge() >= age)
                .collect(Collectors.toList());
        personController.printPersonInfo(result);
    }

    private void editPersonBirthdayByName() {
        System.out.print("請輸入姓名 生日年 月 日： ");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        int yyyy = sc.nextInt();
        int mm = sc.nextInt();
        int dd = sc.nextInt();
        Person person = personController.getPersonByName(name);
        if (person == null) {
            System.out.println("查無此人！");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            person.setBirth(sdf.parse(String.format("%d/%d/%d", yyyy, mm, dd)));
            personController.removePersonByName(name);
            personController.addPerson(person.getName(), person.getBirth());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("修改成功！");
    }

    private void removePersonByName() {
        System.out.print("請輸入姓名：");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        Person person = personController.getPersonByName(name);
        if (person == null) {
            System.out.println("查無此人！");
            return;
        }
        if (personController.removePersonByName(name)) {
            System.out.println("已刪除 " + name);
        } else {
            System.out.println("刪除失敗");
        }
    }

    public void start() {
        while (!stop) {
            menu();
        }
    }
}
