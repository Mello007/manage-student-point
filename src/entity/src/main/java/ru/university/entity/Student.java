package ru.university.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.TABLE) @Column(name = "student_id")  private Long studentId;
    @Column private int estimate;
    @Column private String fullName;
    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="student_date",
            joinColumns={@JoinColumn(name="student_id")},
            inverseJoinColumns={@JoinColumn(name="date_id")})
    private List<Attendance> dateList;
}