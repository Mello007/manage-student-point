package ru.university.service;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.university.entity.Teacher;

import java.util.List;


@Service
public class TeacherService {
    @Autowired SessionFactory sessionFactory;

    @Transactional
    public Teacher createTeacher(Teacher teacher) {
        sessionFactory.getCurrentSession().save(teacher);
        return teacher;
    }

    public Teacher findByName(String login){
        Query query = sessionFactory.getCurrentSession().createQuery("from Teacher where login = :login");
        query.setParameter("login", login);
        Teacher teacher = (Teacher)query.uniqueResult();
        return teacher;
    }

    @Transactional
    public List<Teacher> getAll(){
        List<Teacher> teachers = sessionFactory.getCurrentSession().createCriteria(Teacher.class).list();
        return teachers;
    }

    @Transactional
    public int deleteTeaher (String fullname){
        Query query1 = sessionFactory.getCurrentSession().createQuery("delete from Teacher WHERE fullName = :fullname");
        query1.setParameter("fullname", fullname);
        return query1.executeUpdate();
    }
}
