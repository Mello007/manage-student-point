package ru.university.entity;

import lombok.Data;

@Data
public class Student extends Human{
    private Human human;
    private int estimate;
}
