package com.study.springcore.coursework2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JsonDB {
    private static final Path PATH = Paths.get("src/main/java/com/study/springcore/case8/person.json");
    @Autowired
    private Gson gson;

    public List<Person> queryAll() throws Exception {
        String jsonStr = Files.readAllLines(PATH).stream().collect(Collectors.joining());
        Type type = new TypeToken<ArrayList<Person>>() {
        }.getType();
        List<Person> people = gson.fromJson(jsonStr, type);

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        int curYear = calendar.get(Calendar.YEAR);
        for (Person person : people) {
            calendar.setTime(person.getBirth());
            int birthYear = calendar.get(Calendar.YEAR);
            int age = curYear - birthYear;
            person.setAge(age);
        }

        return people;
    }

    public boolean add(Person person) throws Exception {
        List<Person> people = queryAll();
        if (people.contains(person)) return false;
        people.add(person);
        String newJsonStr = gson.toJson(people);
        Files.write(PATH, newJsonStr.getBytes(StandardCharsets.UTF_8));
        return true;
    }

    public boolean remove(Person person) throws Exception {
        List<Person> people = queryAll();
        boolean check = people.remove(person);
        String newJsonStr = gson.toJson(people);
        Files.write(PATH, newJsonStr.getBytes(StandardCharsets.UTF_8));
        return check;
    }
}
