package ru.university.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity @Table
// Класс студент, в котором описаны поля: id, оценка, полное имя и оценка за доп. задания
public class Student extends BaseEntity {
    private String fullName;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) @JoinTable private List<Estimate> estimate;
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL) @JoinTable private List<Attendance> dateList;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) @JoinTable private List<Estimate> extensionEstimate;
}