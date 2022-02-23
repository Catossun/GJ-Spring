package com.study.springmvc.coursework6.service;

import com.study.springmvc.coursework6.entity.Exam;
import com.study.springmvc.coursework6.entity.ExamSubject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ExamService {
    private final List<Exam> exams = new CopyOnWriteArrayList<>();
    private final List<ExamSubject> examSubjects = new CopyOnWriteArrayList<>();

    {
        examSubjects.add(new ExamSubject("808", "JavaSE 8 OCP I"));
        examSubjects.add(new ExamSubject("809", "JavaSE 8 OCP II"));
        examSubjects.add(new ExamSubject("819", "JavaSE 11 OCP"));
        examSubjects.add(new ExamSubject("900", "JavaEE 7 OCP"));
    }

    public List<ExamSubject> queryExamSubjects() {
        return examSubjects;
    }

    public List<Exam> query() {
        return exams;
    }

    public Optional<Object> get(int index) {
        if (index < 0 || exams.size() == 0 || index >= exams.size()) return Optional.empty();
        return Optional.ofNullable(exams.get(index));
    }

    public Boolean add(Exam exam) {
        int previousSize = exams.size();
        exams.add(exam);
        int nextSize = exams.size();
        return nextSize > previousSize;
    }

    public synchronized Boolean update(int index, Exam exam) {
        if (index < 0 || exams.size() == 0 || index >= exams.size()) return false;
        exams.set(index, exam);
        return true;
    }

    public synchronized Boolean updateNote(int index, String newNote) {
        if (index < 0 || exams.size() == 0 || index >= exams.size()) return false;
        Exam exam = exams.get(index);
        exam.setExamNote(newNote);
        return true;
    }

    public Boolean delete(int index) {
        if (index < 0 || exams.size() == 0 || index >= exams.size()) return false;
        exams.remove(index);
        return true;
    }
}
