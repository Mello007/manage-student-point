package ru.university.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter
@Entity @Table  //Аннотация, которая создает отдельную таблицу в БД
// Класс оценка, в котором описаны поля: id, оценка, дата
public class Estimate extends BaseEntity {
    private int estimate;
    private Date date;
}
