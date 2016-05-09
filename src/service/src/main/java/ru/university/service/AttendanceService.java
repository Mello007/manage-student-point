package ru.university.service;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.university.entity.Attendance;
import ru.university.entity.Student;
import ru.university.util.TimeIgnoringComparator;



@Service //Аннотация, которая показывает, что данный Класс является сервисом

// Публичный класс Управление посещаемостью.
public class AttendanceService {
    @Autowired SessionFactory sessionFactory; // Внедряем бин SessionFactory через который мы взаимодействуем с БД

    /**
     * Метод, который добавляет уважительный пропуск студенту
     * @param studentId
     * @param attendance
     */
    @Transactional //Аннотация, которая открывает транзакцию при работе с БД
    public void addAttendanceToStudent(long studentId, Attendance attendance) {
        TimeIgnoringComparator comparator = new TimeIgnoringComparator(); //Создаем объект класса TimeIgnoringComparator для сравнения времени
        Student student = sessionFactory.getCurrentSession().get(Student.class, studentId);
        List<Attendance> attendances = student.getDateList();
        boolean finded = false;
        for (Attendance attendancc : attendances){
            if (comparator.compare(attendancc.getDate(), attendance.getDate())) { //Сравниваем даты для того чтобы определить нужно ли создавать новую или в текущую отметку вносить исправления
                attendancc.setRespectCause(attendance.getRespectCause()); //Изменяем оценку
                finded = true; //флаг, что дата найдена
            }
        }
        if (!finded) {
            attendances.add(attendance); //если не найдена дата, то мы создаем новую
        }
        sessionFactory.getCurrentSession().merge(student);
    }

    /**
     * Метод, который добавляет неуважительный пропуск к студенту
     * @param studentId Id студента, для которого будет изменена оценка
     * @param attendance
     */
    @Transactional
    public void addNotRespectAttendanceToStudent(long studentId, Attendance attendance) {
        TimeIgnoringComparator comparator = new TimeIgnoringComparator();
        Student student = sessionFactory.getCurrentSession().get(Student.class, studentId);
        List<Attendance> attendances = student.getDateList();
        boolean finded = false;
        for (Attendance attendancc : attendances){
            if (comparator.compare(attendancc.getDate(), attendance.getDate())) {
                attendancc.setNotRespectCause(attendance.getNotRespectCause());
                finded = true;
            }
        }
        if (!finded) {
            attendances.add(attendance);
        }
        sessionFactory.getCurrentSession().merge(student);
    }
}
