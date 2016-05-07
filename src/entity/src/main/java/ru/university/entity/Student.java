package ru.university.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table

// Класс студент, в котором описаны поля: id, оценка, полное имя и оценка за доп. задания
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.TABLE) @Column(name = "student_id")  private Long studentId;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) @JoinTable(name="student_estimate",
            joinColumns={@JoinColumn(name="student_id")},
            inverseJoinColumns={@JoinColumn(name="estimate_id")})
    private List<Estimate> estimate;
    @Column  private String fullName;
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="student_date",
            joinColumns={@JoinColumn(name="student_id")},
            inverseJoinColumns={@JoinColumn(name="date_id")})
    private List<Attendance> dateList;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) @JoinTable(name="student_extension_estimate",
            joinColumns={@JoinColumn(name="student_id")},
            inverseJoinColumns={@JoinColumn(name="estimate_id")})
    private List<Estimate> extensionEstimate;
}