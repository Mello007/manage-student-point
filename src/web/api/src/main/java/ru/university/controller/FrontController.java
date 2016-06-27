package ru.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //Говорит о том, что мы будем возвращать представлене в наших методах
public class FrontController {
    @RequestMapping(value = "/")
    public String getMain() {
        return "resources/index.html";
    }
}

