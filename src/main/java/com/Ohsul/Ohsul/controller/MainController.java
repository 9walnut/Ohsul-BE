package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.dto.BarListDTO;
import com.Ohsul.Ohsul.service.MainAlcoholService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/main")
public class MainController {
    @Autowired
    MainAlcoholService mainAlcoholService;

    @GetMapping("/{curAddress}")
    public ResponseEntity<?> getBarByAddress (@PathVariable String curAddress) {
       try {
           return ResponseEntity.ok().body(mainAlcoholService.getAllBarByAddress(curAddress));
       } catch(Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    // 별점 평균이 4점 이상인 가게 정보 반환
    @GetMapping("/hotBar")
    public ResponseEntity<?> getBarByScore () {
        try {
            return ResponseEntity.ok().body(mainAlcoholService.getAllBarByScore());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
