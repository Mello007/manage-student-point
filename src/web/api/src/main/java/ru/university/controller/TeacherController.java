package ru.university.controller;


import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.university.entity.Student;
import ru.university.entity.Teacher;
import ru.university.service.StudentService;
import ru.university.service.TeacherService;

import java.util.List;


@Secured("ROLE_TEACHER")
@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Teacher add(@RequestParam String fullName, @RequestParam String login, @RequestParam String password){
        return teacherService.createTeacher(fullName, login, password);
    }


    @RequestMapping(value = "test", method = RequestMethod.GET)
    public void getSecurity(){
        System.out.println("Hello world");
    }
}
