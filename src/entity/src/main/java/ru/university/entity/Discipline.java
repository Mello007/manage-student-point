package ru.university.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Discipline {
    private Long Id;
    private String name;
    private Teacher administrator;
    private List<Group> listGroups = new ArrayList<>();
}
