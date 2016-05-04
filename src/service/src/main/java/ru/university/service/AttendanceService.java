package ru.university.service;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.university.entity.Attendance;
import ru.university.entity.Student;

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
}
