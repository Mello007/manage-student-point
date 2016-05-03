package ru.university.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "estimate")
public class Estimate {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE) @Column(name = "estimate_id")  private Long estimateId;
    @Column private int estimate;
    @Column private Date date;
}
