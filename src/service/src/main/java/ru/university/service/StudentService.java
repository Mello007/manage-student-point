package ru.university.service;


import org.hibernate.Session;
import ru.university.entity.Student;
import ru.university.service.hibernate.HibernateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    public StudentService() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
    }

    public boolean add(Student student) {

        return false;

    }

    public Student find(Student student) {
        return null;
    }


    public boolean delete(Student student) {
        return false;

    }

    public List<Student> getAll() {
        return null;

    }

    public boolean addEstimate(Student student) {
        return false;

    }

}