package ru.university.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class Teacher {
    @Id @GeneratedValue(strategy = GenerationType.TABLE) @Column(name = "teacher_id")  private Long teacherId;
    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="teacher_group",
            joinColumns={@JoinColumn(name="teacher_id")},
            inverseJoinColumns={@JoinColumn(name="group_id")})
    private List<Group> groups;
    @Column private String login;
    @Column private String password;
    @Column private String fullName;
}