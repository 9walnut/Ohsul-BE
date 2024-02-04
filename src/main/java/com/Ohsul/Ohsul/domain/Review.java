package com.Ohsul.Ohsul.domain;

import com.Ohsul.Ohsul.entity.Bar;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "review")
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer reviewId;
  private String content;
  private Integer score;
  private String nickname;
  private String password;
  private String reviewImg;

  @ManyToOne
  @JoinColumn(name = "barId")
  private Bar bar;

}
