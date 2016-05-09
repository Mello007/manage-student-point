package ru.university.service;


import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.Query;
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

    public int delete(String fullname) {
        Query query1 = sessionFactory.getCurrentSession().createQuery("delete from Student WHERE fullName = :fullname");
        query1.setParameter("fullname", fullname);
        return query1.executeUpdate();
    }

    @Transactional
    public List<Student> getAll() {
        Query query = sessionFactory.openSession().createQuery("from Student");
        List<Student> students = query.list();
        for (Student students1 : students) {
            students1.getEstimate();
            students1.getExtensionEstimate();
            students1.getDateList();
        }
        return students;
    }

    public boolean addEstimate(Student student) {
        return false;
    }

    @Transactional
    public Student createStudent(Student student) {
        sessionFactory.getCurrentSession().save(student);
        return student;
    }

    @Transactional
    public List<Student> getWitInformationByDate(Date date) {
        return null;
    }
}