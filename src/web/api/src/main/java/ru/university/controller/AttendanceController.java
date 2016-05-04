package ru.university.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.university.controller.dto.AttendanceDTO;
import ru.university.entity.Attendance;
import ru.university.entity.Student;
import ru.university.service.AttendanceService;
import ru.university.service.StudentService;

@RestController
@RequestMapping("attendance")
public class AttendanceController {
    @Autowired AttendanceService attendanceService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "respect/add", method = RequestMethod.POST)
    public void addRespect(@RequestBody AttendanceDTO attendanceDTO) throws ParseException {
        Attendance attendance = new Attendance();
        if (attendanceDTO.getDate().equals("null")) {
            attendance.setDate(new Date());
        } else {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(attendanceDTO.getDate());
            attendance.setDate(date);
        }
        attendance.setRespectCause(attendanceDTO.getRespect());
        attendanceService.addAttendanceToStudent(attendanceDTO.getId(), attendance);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "notrespect/add", method = RequestMethod.POST)
    public void addNotRespect(@RequestBody AttendanceDTO attendanceDTO) throws ParseException {
        Attendance attendance = new Attendance();
        if (attendanceDTO.getDate().equals("null")) {
            attendance.setDate(new Date());
        } else {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(attendanceDTO.getDate());
            attendance.setDate(date);
        }
        attendance.setNotRespectCause(attendanceDTO.getNotRespectCause());
        attendanceService.addNotRespectAttendanceToStudent(attendanceDTO.getId(), attendance);
    }
}
