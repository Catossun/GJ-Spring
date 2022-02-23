package com.study.springmvc.coursework6.entity;

public enum ExamSlot {
    MORNING("早上"),
    AFTERNOON("下午"),
    NIGHT("晚上");

    private final String displayValue;

    ExamSlot(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
