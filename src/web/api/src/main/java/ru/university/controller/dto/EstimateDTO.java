package ru.university.controller.dto;

import lombok.Data;

@Data
public class EstimateDTO {
    private Long id;
    private int estimate;
    private String date;
}
