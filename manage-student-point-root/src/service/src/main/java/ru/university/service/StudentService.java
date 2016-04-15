package ru.university.service;


import ru.university.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private static final List<Student> ALL_STUDENTS = new ArrayList<>();

    public boolean add(Student student){
        return ALL_STUDENTS.add(student);
    }

    public boolean find(Student student){
        return ALL_STUDENTS.contains(student);
    }

    public boolean delete(Student student){
        return ALL_STUDENTS.remove(student);
    }

    public List<Student> look(){
        return ALL_STUDENTS;
    }
}

