package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.service.TodayAlcoholService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/ohsul")
@RestController
public class TodayAlcoholController {

    @Autowired
    TodayAlcoholService todayAlcoholService;

    @GetMapping("/{sulAddress}")
    public ResponseEntity<?> searchBarByAddress (@PathVariable String sulAddress) {
        try {
            return ResponseEntity.ok().body(todayAlcoholService.searchBarByAddress(sulAddress));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
