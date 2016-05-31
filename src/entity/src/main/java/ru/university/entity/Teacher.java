package ru.university.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity @Table
//Класс учитель, в котором описаны поля: логин, пароль, полное имя.
public class Teacher extends BaseEntity {
    private String login;
    private String password;
    private String fullName;
    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL) @JoinTable private List<Group> groups;
}