package ru.university.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "estimate")  //Аннотация, которая создает отдельную таблицу в БД
// Класс оценка, в котором описаны поля: id, оценка, дата
public class Estimate {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE) @Column(name = "estimate_id")  private Long estimateId;
    @Column private int estimate;
    @Column private Date date;
}
