package com.Ohsul.Ohsul.dto;

import com.Ohsul.Ohsul.entity.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {
  @NotBlank(message = "아이디를 입력해주세요")
  private String userId;
  @NotBlank(message = "비밀번호를 입력해주세요")
  private String userPw;
  @NotBlank(message = "이름을 입력해주세요")
  private String userName;
  @NotBlank(message = "닉네임을 입력해주세요")
  private String userNickname;

  // 비밀번호 암호화
  public User toEntity(String salt){
    return User.builder()
            .userId(this.userId)
            .userPw(this.userPw)
            .userName(this.userName)
            .userNickname(this.userNickname)
            .build();
  }
}
