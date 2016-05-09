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

@RestController() //Указываем, что это будет контроллером
@RequestMapping("estimatecontroller")
public class EstimateController {
    @Autowired EstimateService estimateService; //Внедряем бин

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void add(@RequestBody EstimateDTO estimate) throws ParseException {
        Long userId = estimate.getId();
        estimateService.add(parseEstimate(estimate), userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "addext", method = RequestMethod.POST)
    public void addExtension(@RequestBody EstimateDTO estimate) throws ParseException {
        Long userId = estimate.getId();
        estimateService.addExtension(parseEstimate(estimate), userId);
    }

    private Estimate parseEstimate(EstimateDTO estimate) throws ParseException {
        Estimate estimat = new Estimate();
        if (estimate.getDate().equals("null")) {
            estimat.setDate(new Date());
        } else {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(estimate.getDate());
            estimat.setDate(date);
        }
        estimat.setEstimate(estimate.getEstimate());
        return estimat;
    }
}
