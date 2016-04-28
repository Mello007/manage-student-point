package ru.university.service;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.university.entity.Student;

import java.util.List;

@Service
public class StudentService {

    @Autowired SessionFactory sessionFactory;

    public StudentService() {

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

    @Transactional
    public List<Student> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(Student.class).list();
    }

    public boolean addEstimate(Student student) {
        return false;
    }
}