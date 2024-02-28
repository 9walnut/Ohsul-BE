package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.service.BarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ohsul")
public class BarInfoController {

    @Autowired
    BarInfoService barInfoService;

    @GetMapping("/bar/{barId}")
    public ResponseEntity<?> getBarInfo (@PathVariable Integer barId) {
        try {
            return ResponseEntity.ok().body(barInfoService.getBarInfo(barId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
