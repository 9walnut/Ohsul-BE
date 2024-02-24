package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.dto.BarSearchDTO;
import com.Ohsul.Ohsul.service.OhSulService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/ohsul")
public class SearchAlcoholController {

    @Autowired
    OhSulService ohSulService;
    
    @PostMapping("/searchAlcohol")
    public ResponseEntity<?> searchAlcohol (@RequestBody BarSearchDTO request) {
        return ohSulService.getBarScoreAndReviewInfo(request.getTelephones(), request.getBarNames());
    }
}
