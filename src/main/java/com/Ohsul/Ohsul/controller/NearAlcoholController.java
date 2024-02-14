package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.service.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ohsul")
public class NearAlcoholController {
  private NearAlcoholService nearAlcoholService;

  @GetMapping("/{curAddress}")
  public String getNearPlace(@RequestParam String lat, @RequestParam String lng) throws IOException{
    return nearAlcoholService.getNearPlace(lat, lng);
  }

}
