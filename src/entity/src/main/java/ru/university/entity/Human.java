package ru.university.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
public class Human {
    @Column
    protected Long Id;
    @Column
    protected String fullName;
    @Column
    protected int age;
}
