package ru.university.controller;


import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.university.controller.dto.DateStudent;
import ru.university.controller.dto.SimpleStudent;
import ru.university.controller.dto.StudentDTO;
import ru.university.entity.*;
import ru.university.service.StudentService;

import java.util.List;
import ru.university.util.TimeIgnoringComparator;

@RestController //Указываем, что это будет контроллером
@RequestMapping("student") //
public class StudentController {
    @Autowired StudentService studentService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Student> getStudent(){
       return studentService.getAll();
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Student add(@RequestBody SimpleStudent simpleStudent){
        Student student = new Student();
        student.setFullName(simpleStudent.getFullName());
        List<Estimate> estimates = new ArrayList<Estimate>();
        Estimate estimate = new Estimate();
        estimate.setEstimate(simpleStudent.getEstimate());
        estimate.setDate(new Date());
        estimates.add(estimate);
        student.setEstimate(estimates);
        return studentService.createStudent(student);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public int deleteStudent(@RequestBody StudentDTO studentDTO){
        return studentService.delete(studentDTO.getFullname());
    }

    /**
     * Загружаем оценки студентов по выбранной дате
     * @param date 
     * @return
     */
    @RequestMapping(value = "date", method = RequestMethod.GET)
    public List<DateStudent> getStudentsByDate(@RequestParam(required = false, value = "date") Date date){ 
        if (date == null) { //если дата null
            date = new Date(); //создаем новую дату
        }
        TimeIgnoringComparator timeIgnoringComparator = new TimeIgnoringComparator(); 
        List<Student> students = studentService.getAll();
        List<DateStudent> dateStudents = new ArrayList<DateStudent>(); //Создаем новый dateStudents
        for (Student student : students) { //Итерируемся по студентам
            DateStudent dateStudent = new DateStudent(); //Создаем новый объект класса
            dateStudent.setFullName(student.getFullName()); //Берем значение имени из studentSTOP и присваиваем в dateStudent
            Attendance attendanceProperty = null; //Создаем новый экземпляр класс Attendance
            for (Attendance attendance : student.getDateList()) { //Итерируемся
                if (timeIgnoringComparator.compare(attendance.getDate(), date)) { //Сравнием дату с сайта и с БД
                    attendanceProperty = attendance; //установили ссылку attendanceProperty на  attendance
                }
            }
            Estimate estimation = null;
            for (Estimate estimate : student.getEstimate()) {
                if (timeIgnoringComparator.compare(estimate.getDate(), date)) {
                    estimation = estimate;
                }
            }
            Estimate extEstimation = null;
            for (Estimate estimate : student.getExtensionEstimate()) {
                if (timeIgnoringComparator.compare(estimate.getDate(), date)) {
                    extEstimation = estimate;
                }
            }
            dateStudent.setDateList(attendanceProperty); //Добавляем оценку в dateStudent
            dateStudent.setStudentId(student.getStudentId());
            dateStudent.setEstimate(estimation);
            dateStudent.setExtEstimate(extEstimation);
            dateStudents.add(dateStudent);
        }
        return dateStudents;
    }
}
