package com.study.springcore.coursework2;

import java.util.Date;
import java.util.List;

public interface PersonService {
    boolean append(String name, Date birth);

    boolean append(Person person);

    List<Person> findAllPersons();

    Person getPerson(String name);

    boolean remove(Person person);
}
