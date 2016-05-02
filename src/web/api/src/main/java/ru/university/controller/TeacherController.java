package ru.university.controller;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.university.controller.dto.SimpleTeacherDTO;
import ru.university.controller.dto.TeacherToCreateDTO;
import ru.university.entity.Student;
import ru.university.entity.Teacher;
import ru.university.service.StudentService;
import ru.university.service.TeacherService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


//@Secured("ROLE_TEACHER")
@RestController
@RequestMapping(value = "teacher", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TeacherController {
    @Autowired TeacherService teacherService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Teacher add(@RequestBody Teacher teacher){
        return teacherService.createTeacher(teacher);
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<SimpleTeacherDTO> getTeacher(){
        List<SimpleTeacherDTO> simpleTeacherDTOs = new ArrayList<SimpleTeacherDTO>();
        List<Teacher> teachers = teacherService.getAll();
        for (Teacher teacher : teachers){
            SimpleTeacherDTO simpleTeacherDTO = new SimpleTeacherDTO(teacher);
            simpleTeacherDTOs.add(simpleTeacherDTO);
        }
        return simpleTeacherDTOs;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Teacher deleteTeacher(String fullname){
        return teacherService.deleteTeaher(fullname);
    }
}