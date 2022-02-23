package com.study.springmvc.coursework6.entity;

public enum ExamPayStatus {
    PAY("已付款"),
    NO_PAY("未付款");

    private final String displayValue;

    ExamPayStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    @Override
    public String toString() {
        return "ExamPayStatus{" +
                "displayValue='" + displayValue + '\'' +
                '}';
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
