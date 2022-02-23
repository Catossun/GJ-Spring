package com.study.springmvc.coursework6.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Arrays;
import java.util.Date;

public class Exam {
    private String studentId;
    private String examId;
    private ExamSlot[] examSlots;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date examDate;


    private ExamPayStatus examPay;
    private String examNote;

    @Override
    public String toString() {
        return "Exam{" +
                "studentId='" + studentId + '\'' +
                ", examId='" + examId + '\'' +
                ", examSlot=" + Arrays.toString(examSlots) +
                ", examDate=" + examDate +
                ", examPay=" + examPay +
                ", examNote='" + examNote + '\'' +
                '}';
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public ExamSlot[] getExamSlots() {
        return examSlots;
    }

    public void setExamSlots(ExamSlot[] examSlots) {
        this.examSlots = examSlots;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public ExamPayStatus getExamPay() {
        return examPay;
    }

    public void setExamPay(ExamPayStatus examPay) {
        this.examPay = examPay;
    }

    public String getExamNote() {
        return examNote;
    }

    public void setExamNote(String examNote) {
        this.examNote = examNote;
    }
}
