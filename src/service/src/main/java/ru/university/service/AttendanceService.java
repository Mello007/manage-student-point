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
        Student student = sessionFactory.getCurrentSession().get(Student.class, studentId);
        List<Attendance> attendances = student.getDateList();
        attendances.add(attendance);
        student.setDateList(attendances);
    }

    @Transactional
    public void addNotRespectAttendanceToStudent(long studentId, Attendance attendance) {
        TimeIgnoringComparator comparator = new TimeIgnoringComparator();
        Student student = sessionFactory.openSession().load(Student.class, studentId);
        List<Attendance> attendances = student.getDateList();
        Attendance currentAttendance = null;
        for (Attendance attendancc : attendances){
            if (comparator.compare(attendancc.getDate(), attendance.getDate())) {
                currentAttendance = attendancc;
            }
        }
        if (currentAttendance == null) {
            attendances.add(attendance);
        } else {
            currentAttendance.setNotRespectCause(attendance.getNotRespectCause());
            sessionFactory.getCurrentSession().persist(currentAttendance);
        }
    }
}
