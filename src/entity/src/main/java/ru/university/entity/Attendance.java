package ru.university.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity //аннотация, которая показывает что класс является сущностью
@Table //аннотация, котора создает для класса отдельную таблицу в БД
@Data //аннотация, которая позволяет автоматически создавать Getter и Setter для наших полей
// Класс - Посещаемость, в котором есть поля даты, поле отсутствия по уважительной причине и по неуважительной причине.
public class Attendance {
    @Id @GeneratedValue (strategy = GenerationType.TABLE) @Column(name = "attendance_id") private Long attendanceId;
    @Column private Date date;
    @Column private int respectCause;
    @Column private int notRespectCause;
}
