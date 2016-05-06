package ru.university.controller.dto;

import java.util.List;
import javax.persistence.*;
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
