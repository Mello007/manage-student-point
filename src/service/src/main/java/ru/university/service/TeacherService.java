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
    public Teacher createTeacher(String fullName, String login, String password) {
        Teacher teacher = new Teacher();
        teacher.setFullName(fullName);
        teacher.setLogin(login);
        teacher.setPassword(password);
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

    public Teacher deleteTeaher (String fullname){
        Teacher teacher = new Teacher();
        Query query1 = sessionFactory.getCurrentSession().createQuery("DELETE FROM Teacher WHERE fullname = :fullname");
        query1.setParameter("fullname", fullname);
        return teacher;
    }
}
