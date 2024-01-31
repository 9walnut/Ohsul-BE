package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.domain.Store;
import com.Ohsul.Ohsul.dto.StoreDto;
import com.Ohsul.Ohsul.repository.StoreRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class MapCrawlerService {
  private final StoreRepository storeRepository;

  @Autowired
  public MapCrawlerService(StoreRepository storeRepository){
    this.storeRepository = storeRepository;
  }
  public List<StoreDto> crawlMapStore(){
    System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    WebDriver driver = new ChromeDriver(options);

    //가져올 url
    driver.get("");
    // 가져올 정보
    List<WebElement> elements = driver.findElements(By.cssSelector("tr.top"));
    // 반환할 Dto 리스트 생성
    List<StoreDto> stores = new ArrayList<>();

    // 가져올 정보들
    for (WebElement storeElement : elements){
      WebElement anchorElement = storeElement.findElement(By.cssSelector("td.lAlign > a"));
      String title = anchorElement.getText();
      String link = anchorElement.getAttribute("href");

      System.out.println("title = " + title);
      System.out.println("link = " + link);

      WebElement dateElement = storeElement.findElement(By.cssSelector("td:nth-child(2)"));
      String dateText = dateElement.getText();
      LocalDate date = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("yyyy.MM.dd"));

      Store store = Store.builder().title(title).link(link).date(date).build();

      storeRepository.save(store);

      stores.add(new StoreDto(store));
    }
    driver.quit();

    return stores;

  }
}
