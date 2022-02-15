package com.study.springcore.coursework2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

    public void addPerson(String name, int yyyy, int mm, int dd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date birth = sdf.parse(yyyy + "/" + mm + "/" + dd);
            addPerson(name, birth);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPerson(String name, Date birth) {
        boolean check = personService.append(name, birth);
        System.out.println("add person: " + check);
    }

    public void printPersonInfo(Person... people) {
        printPersonInfo(Arrays.stream(people).collect(Collectors.toList()));
    }

    public void printPersonInfo(List<Person> people) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("+--------------+---------+--------------+");
        System.out.println("|     name     |   age   |   birthday   |"); // 12, 7, 12
        System.out.println("+--------------+---------+--------------+");
        for (Person p : people) {
            String birthday = sdf.format(p.getBirth());
            System.out.printf("| %-12s | %7d | %12s |\n", p.getName(), p.getAge(), birthday);
            System.out.println("+--------------+---------+--------------+");
        }
    }

    public Person getPersonByName(String name) {
        return personService.getPerson(name);
    }

    public List<Person> getAllPerson() {
        return personService.findAllPersons();
    }

    public boolean removePersonByName(String name) {
        Person person = personService.getPerson(name);
        return personService.remove(person);
    }
}
