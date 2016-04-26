package ru.university.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.university.entity.*;
import ru.university.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired StudentService studentService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void add(){
        System.out.println("add");
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Student> getStudent(){
       return studentService.getAll();
    }
}
