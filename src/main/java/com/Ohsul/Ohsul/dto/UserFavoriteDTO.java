package com.Ohsul.Ohsul.dto;

import lombok.*;

import java.util.*;

// 즐겨찾기 기능과 혼용 - 재사용 염두
@Getter
@Setter
public class UserFavoriteDTO {
  private Integer userNumber;
  private String userId;
  private String userName;
  private String userNickname;
  private String userPw;
  private List<FavoriteDTO> favorites;
}
