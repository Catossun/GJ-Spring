package com.study.springcore.coursework1;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Teacher {
    private Integer id;
    private String name;
    private Set<Clazz> clazzes;
    private List<String> experties;
    private Map<String, Integer> salary;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clazzes=" + clazzes +
                ", experties=" + experties +
                ", salary=" + salary +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Clazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(Set<Clazz> clazzes) {
        this.clazzes = clazzes;
    }

    public List<String> getExperties() {
        return experties;
    }

    public void setExperties(List<String> experties) {
        this.experties = experties;
    }

    public Map<String, Integer> getSalary() {
        return salary;
    }

    public void setSalary(Map<String, Integer> salary) {
        this.salary = salary;
    }
}
