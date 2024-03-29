package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.service.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ohsul/near")
public class NearAlcoholController {

  @Autowired
  NearAlcoholService nearAlcoholService;

  @PostMapping("")
  public ResponseEntity<List<BarListDTO>> getNearBar(@RequestBody List<BarSearchDTO> requests){
    List<BarListDTO> bars = nearAlcoholService.findBarsByRequestList(requests);
    return ResponseEntity.ok(bars);
  }
}
