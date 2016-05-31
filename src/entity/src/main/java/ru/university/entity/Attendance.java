package ru.university.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity @Table
@Getter @Setter //аннотация, которая позволяет автоматически создавать Getter и Setter для наших полей
// Класс - Посещаемость, в котором есть поля даты, поле отсутствия по уважительной причине и по неуважительной причине.
public class Attendance extends BaseEntity {
    private Date date;
    private int respectCause;
    private int notRespectCause;
}
