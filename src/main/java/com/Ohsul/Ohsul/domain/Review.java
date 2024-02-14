package com.Ohsul.Ohsul.domain;

import com.Ohsul.Ohsul.entity.BarEntity;
import com.Ohsul.Ohsul.entity.UserEntity;
import lombok.*;

@Getter
public class Review {
  private Integer reviewId;
  private String content;
  private Integer score;
  private String reviewImg;
  private String nickname;
  private String reviewPw;
  private BarEntity barEntity;
  private UserEntity userEntity;
}
