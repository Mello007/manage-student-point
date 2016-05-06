package ru.university.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Data
@Entity
@Table(name = "group_table")
public class Group {
    @Id @GeneratedValue(strategy = GenerationType.TABLE) @Column(name = "group_id")  private Long groupId;
    @Column private String name;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="group_student",
            joinColumns={@JoinColumn(name="group_id")},
            inverseJoinColumns={@JoinColumn(name="student_id")})
    private List<Student> students;
}