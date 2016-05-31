package ru.university.controller;

//Подключаем необходимые библиотеки
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.university.dto.SimpleTeacherDTO;
import ru.university.dto.TeacherDTO;
import ru.university.entity.Teacher;
import ru.university.mapper.DTOMapper;
import ru.university.service.TeacherService;
import java.util.ArrayList;
import java.util.List;

@Secured("ROLE_TEACHER") //Указываем, что данный класс недоступен неавторизованным пользователям
@RestController //Указываем, что это будет контроллером
@RequestMapping(value = "teacher", produces = MediaType.APPLICATION_JSON_UTF8_VALUE) //Указываем URL для данного класса
public class TeacherController {
    @Autowired TeacherService teacherService;
    @Autowired DTOMapper dtoMapper;
    /**
     * Метод, который принимает преподавателя с сайта и передает в TeacherService
     * @param teacher
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Teacher add(@RequestBody Teacher teacher){
        return teacherService.createTeacher(teacher);
    }

    /**
     * Метод, который показывает всех преподавателей
     * @return
     */
    @RequestMapping(value = "all", method = RequestMethod.GET) //Указываем что данный метод доступен по URL - all и он отправляет GET запрос
    public List<SimpleTeacherDTO> getTeacher(){
        return dtoMapper.toSimpleTeacherDTOs(teacherService.getAll()); //возвращаем список преподавателей
    }

    /**
     * Метод, который передает ФИО преподавателя для удаления в TeacherService
     * @param teacherDTO
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public int deleteTeacher(@RequestBody TeacherDTO teacherDTO){
        return teacherService.deleteTeaher(teacherDTO.getFullname());
    }
}
