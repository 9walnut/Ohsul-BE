package com.Ohsul.Ohsul.domain;

import com.Ohsul.Ohsul.entity.BarEntity;
import com.Ohsul.Ohsul.entity.UserEntity;
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
  private BarEntity barEntity;

  @ManyToOne
  @JoinColumn(name = "userNumber")
  private UserEntity userEntity;
}
