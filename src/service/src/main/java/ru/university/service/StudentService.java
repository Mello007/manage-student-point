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

/**
 * Указываем что это Service
 */
@Service
public class StudentService {

    @Autowired SessionFactory sessionFactory;
    /**
     * Метод, который удаляет студента
     * @param fullname Принимает имя
     * @return
     */
    @Transactional
    public int delete(String fullname) {
        Query query1 = sessionFactory.getCurrentSession().createQuery("delete from Student WHERE fullName = :fullname"); // Удаляем студента из БД с помощью HQL языка
        query1.setParameter("fullname", fullname); //Указываем, что в запросе fullname это будет принимаемый fullname
        return query1.executeUpdate(); //Обновляем значения
    }

    /**
     * Метод который показывает всех студентов
     * @return
     */
    @Transactional
    public List<Student> getAll() {
        Query query = sessionFactory.openSession().createQuery("from Student"); //Делаем запрос через HQL
        List<Student> students = query.list(); //ПОлучаем всех студентов
        for (Student students1 : students) { //цикл
            students1.getEstimate(); //получаем оценку
            students1.getExtensionEstimate(); //получаем оценку за доп задания
            students1.getDateList(); //Получаем DateList
        }
        return students; //Возвращаем студентов
    }

    /**
     * Метод, который создает нового студента и сохраняет его в БД
     * @param student принимает студента
     * @return
     */
    @Transactional
    public Student createStudent(Student student) {
        sessionFactory.getCurrentSession().save(student); //Сохраняем полученного студента в БД
        return student;
    }
}