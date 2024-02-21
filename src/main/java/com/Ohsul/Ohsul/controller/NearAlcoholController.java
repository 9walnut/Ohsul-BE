package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ohsul/near")
public class NearAlcoholController {
  private final NearAlcoholService nearAlcoholService;

  @PostMapping("")
  public ResponseEntity<List<BarListDTO>> getNearBar(@RequestBody List<String> telephone){
    List<BarListDTO> bars = nearAlcoholService.findBarsByTelephone(telephone);
    return ResponseEntity.ok(bars);
  }
}
