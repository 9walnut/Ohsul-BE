package com.Ohsul.Ohsul.dto;

import com.Ohsul.Ohsul.entity.ReviewEntity;
import lombok.*;

import java.util.*;

@Getter
@Setter
public class UserReviewDTO {
  private String userId;
  private String userName;
  private String userNickname;
  private List<ReviewEntity> reviews;

}
