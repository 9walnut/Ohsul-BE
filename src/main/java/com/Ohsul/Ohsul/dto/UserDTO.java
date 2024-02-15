package com.Ohsul.Ohsul.dto;

import com.Ohsul.Ohsul.entity.*;
import lombok.*;

@Getter
@Builder
public class UserDTO {
  private Integer userNumber;
  private String userId;
  private String userPw;
  private String userName;
  private String userNickname;

  public UserDTO(){

  }
  public UserDTO(Integer userNumber, String userId, String userPw, String userName, String userNickname){
    this.userNumber = userNumber;
    this.userId = userId;
    this.userPw = userPw;
    this.userName = userName;
    this.userNickname = userNickname;
  }
}
