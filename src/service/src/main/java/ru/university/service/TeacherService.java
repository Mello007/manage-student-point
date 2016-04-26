package ru.university.service;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.university.entity.Teacher;


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
}
