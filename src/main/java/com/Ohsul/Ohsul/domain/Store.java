//package com.Ohsul.Ohsul.domain;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import lombok.*;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class Store {
//
//  @Id
//  private Long id;
//  private String title;
//  private String link;
//
//  @Column(nullable =false)
//  private LocalDate date;
//
//  @Builder
//  public Store(String title, String link, LocalDate date){
//    this.title = title;
//    this.link = link;
//    this.date = date;
//  }
//
//}
