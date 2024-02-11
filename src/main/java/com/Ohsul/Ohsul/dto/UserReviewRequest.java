package com.Ohsul.Ohsul.dto;

import com.Ohsul.Ohsul.domain.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
public class UserReviewRequest {
  private String userId;
  private String userName;
  private String userNickname;
  private List<Review> reviews;

}
