package com.Ohsul.Ohsul.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Store {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String link;

  @Column(nullable =false)
  private LocalDate date;

  @Builder
  public Store(String title, String link, LocalDate date){
    this.title = title;
    this.link = link;
    this.date = date;
  }

}
