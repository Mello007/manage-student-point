package ru.university.entity;

import lombok.Data;

@Data
public class Student {

    private String fullName;
    private int age;
    private int estimate;

    public Student(String fullName, int age, int estimate) {
        this.fullName = fullName;
        this.age = age;
        this.estimate = estimate;
    }
}
