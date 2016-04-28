package ru.university.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table
@Data
public class Attendance {
    @Id @GeneratedValue (strategy = GenerationType.TABLE) @Column(name = "attendance_id") private Long attendanceId;
    @Column private Date date;
    @Column private Integer studentHere;
}
