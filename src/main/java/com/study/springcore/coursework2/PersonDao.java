package com.study.springcore.coursework2;

import java.util.List;

public interface PersonDao {
    boolean create(Person person);

    List<Person> readAll();

    boolean remove(Person person);
}
