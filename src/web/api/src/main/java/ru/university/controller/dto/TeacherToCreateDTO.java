package ru.university.controller.dto;

import lombok.Data;

@Data
public class TeacherToCreateDTO {
    private String fullName;
    private String login;
    private String password;
}
