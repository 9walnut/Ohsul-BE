package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.service.OhSulService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/ohsul")
public class SearchAlcoholController {

    @Autowired
    OhSulService ohSulService;
    
    @PostMapping("/searchAlcohol")
    public ResponseEntity<List<BarListDTO>> searchAlcohol (@RequestBody List<BarSearchDTO> requests) {
        List<BarListDTO> bars = ohSulService.getBarScoreAndReviewInfo(requests);
        return ResponseEntity.ok(bars);
    }
}
