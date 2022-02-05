package com.study.springcore.coursework2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDaoImpl implements PersonDao {
    @Autowired
    private JsonDB jsonDB;

    @Override
    public boolean create(Person person) {
        boolean check = false;
        try {
            check = jsonDB.add(person);
        } catch (Exception e) {
            e.printStackTrace();
            check = false;
        }
        return check;
    }

    @Override
    public List<Person> readAll() {
        List<Person> people = null;
        try {
            people = jsonDB.queryAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return people;
    }

    @Override
    public boolean remove(Person person) {
        boolean check = false;
        try {
            check = jsonDB.remove(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
