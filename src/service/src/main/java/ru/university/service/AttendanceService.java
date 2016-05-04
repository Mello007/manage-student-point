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

@Service
public class AttendanceService {
    @Autowired
    SessionFactory sessionFactory;


    @Transactional
    public void addAttendanceToStudent(long studentId, Attendance attendance) {
        TimeIgnoringComparator comparator = new TimeIgnoringComparator();
        Student student = sessionFactory.getCurrentSession().get(Student.class, studentId);
        List<Attendance> attendances = student.getDateList();
        boolean finded = false;
        for (Attendance attendancc : attendances){
            if (comparator.compare(attendancc.getDate(), attendance.getDate())) {
                attendancc.setRespectCause(attendance.getRespectCause());
                finded = true;
            }
        }
        if (!finded) {
            attendances.add(attendance);
        }
        sessionFactory.getCurrentSession().merge(student);
    }

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
