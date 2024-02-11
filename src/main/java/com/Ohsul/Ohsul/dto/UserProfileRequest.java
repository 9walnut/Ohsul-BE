package com.Ohsul.Ohsul.dto;

import com.Ohsul.Ohsul.domain.*;
import lombok.*;

import java.util.*;

// 즐겨찾기 기능과 혼용 - 재사용 염두
@Getter
@Setter
public class UserProfileRequest {
  private String userId;
  private String userName;
  private String userNickname;
  private String userPw;
  private List<Favorite> favorites;
}
