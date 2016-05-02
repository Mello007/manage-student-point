package ru.university.controller.dto;

import lombok.Getter;
import lombok.Setter;
import ru.university.entity.Teacher;

@Getter @Setter
public class SimpleTeacherDTO {
    private String fullName;
    public SimpleTeacherDTO(Teacher teacher){
        this.fullName = teacher.getFullName();
    }
}
