package com.Ohsul.Ohsul.domain;

import com.Ohsul.Ohsul.entity.Bar;
import com.Ohsul.Ohsul.entity.User;
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
  private String reviewImg;

  @ManyToOne
  @JoinColumn(name = "barId")
  private Bar bar;

  @ManyToOne
  @JoinColumn(name = "userNumber")
  private User user;
}
