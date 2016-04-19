package ru.university.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table
public class Student extends Human{
    @Column
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