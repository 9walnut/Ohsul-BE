package com.Ohsul.Ohsul.dto;

import com.Ohsul.Ohsul.entity.*;
import lombok.*;

@Getter
@Builder
public class UserDTO {
  private String userId;
  private String userName;
  private String userNickname;
  private String userPw;

  // 비밀번호 암호화
//  public UserEntity toEntity(String salt){
//    return UserEntity.builder()
//            .userId(this.userId)
//            .userPw(salt)
//            .userName(this.userName)
//            .userNickname(this.userNickname)
//            .build();
}
