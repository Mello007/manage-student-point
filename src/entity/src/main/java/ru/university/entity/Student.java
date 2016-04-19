package ru.university.entity;

import lombok.Data;

@Data
public class Student extends Human{
    private int estimate;

    public Student(String fullName, Integer studentAge, Integer studentEstimate) {
        super.age = studentAge;
        super.fullName = fullName;
        this.estimate = studentEstimate;
    }
    public String toString() {
        return "Количество баллов студента " + estimate + "\n" + super.toString();
    }
}