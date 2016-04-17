package ru.university.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Group {
    private Long Id;
    private String name;
    private List<Student> students = new ArrayList<>();
}
