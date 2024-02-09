package com.Ohsul.Ohsul.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
  private String userId;
  private String userPw;
}
