package ru.university.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.university.dto.AttendanceDTO;
import ru.university.entity.Attendance;
import ru.university.service.AttendanceService;


@RestController //Указываем, что это будет контроллером
@RequestMapping("attendance") //Указываем URL для данного класса
public class AttendanceController {
    @Autowired AttendanceService attendanceService;

    @ResponseStatus(HttpStatus.OK) //В случае успешного завершения, метод возвращает ответ сервера: OK
    @RequestMapping(value = "respect/add", method = RequestMethod.POST)
    public void addRespect(@RequestBody AttendanceDTO attendanceDTO) throws ParseException {
        Attendance attendance = new Attendance();
        if (attendanceDTO.getDate().equals("null")) { //Смотрим не получили ли мы строку null
            attendance.setDate(new Date()); //Если мы получили null, тогда создаем новую дату
        } else {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); //Если не получили null, тогда указываем в каком формате пришла дата
            Date date = dateFormat.parse(attendanceDTO.getDate()); //Парсим дату
            attendance.setDate(date); //Добавляем конкретную дату к посещаемости
        }
        attendance.setRespectCause(attendanceDTO.getRespect()); //Добавляем пропуск по уважительной причине
        attendanceService.addAttendanceToStudent(attendanceDTO.getId(), attendance); //Сохраняем пропуски для студентов
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "notrespect/add", method = RequestMethod.POST)
    public void addNotRespect(@RequestBody AttendanceDTO attendanceDTO) throws ParseException {
        Attendance attendance = new Attendance();
        if (attendanceDTO.getDate().equals("null")) {
            attendance.setDate(new Date());
        } else {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date date = dateFormat.parse(attendanceDTO.getDate());
            attendance.setDate(date);
        }
        attendance.setNotRespectCause(attendanceDTO.getNotRespectCause());
        attendanceService.addNotRespectAttendanceToStudent(attendanceDTO.getId(), attendance);
    }
}
