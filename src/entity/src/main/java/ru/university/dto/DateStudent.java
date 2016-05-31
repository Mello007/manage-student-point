package ru.university.dto;

import lombok.Data;
import ru.university.entity.Attendance;
import ru.university.entity.Estimate;

@Data
public class DateStudent {
    private Long studentId;
    private Estimate estimate;
    private Estimate extEstimate;
    private String fullName;
    private Attendance dateList;
}
