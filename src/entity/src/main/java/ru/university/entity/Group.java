package ru.university.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Getter @Setter
@Entity @Table(name = "Groups")
public class Group extends BaseEntity {
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) @JoinTable private List<Student> students;
}