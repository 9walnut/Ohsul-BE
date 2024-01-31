package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.dto.StoreDto;
import com.Ohsul.Ohsul.service.MapCrawlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/crawl")
public class MapCrawlerController {
  private final MapCrawlerService mapCrawlerService;

  @GetMapping("/v1/crawl")
  public List<StoreDto> crawlMapStore(){
    return mapCrawlerService.crawlMapStore();
  }
}
