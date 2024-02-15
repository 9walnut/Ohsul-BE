package com.Ohsul.Ohsul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/main/{curAddress}")
    public String mainView () {
        return "정보정보";
    }
}
