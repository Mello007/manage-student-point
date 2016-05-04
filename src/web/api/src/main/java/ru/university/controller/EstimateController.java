package ru.university.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.university.controller.dto.EstimateDTO;
import ru.university.entity.Estimate;
import ru.university.service.EstimateService;

@RestController()
@RequestMapping("estimatecontroller")
public class EstimateController {
    @Autowired
    EstimateService estimateService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void add(@RequestBody EstimateDTO estimate) throws ParseException {
        Estimate estimat = new Estimate();
        if (estimate.getDate().equals("null")) {
            estimat.setDate(new Date());
        } else {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(estimate.getDate());
            estimat.setDate(date);
        }
        estimat.setEstimate(estimate.getEstimate());
        Long userId = estimate.getId();
        estimateService.add(estimat, userId);
    }
}
