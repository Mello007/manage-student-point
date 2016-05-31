package ru.university.dto;

import lombok.Data;

@Data
public class AttendanceDTO {
    private int id;
    private int respect;
    private int notRespectCause;
    private String date;
}
