package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.entity.*;
import com.Ohsul.Ohsul.service.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class UserController {
  private final UserService userService;

  // 로그인 페이지
//  @GetMapping("/login")
//  public String disLogin(){
//    return "/login";
//  }
  // 로그인 요청
  // 쿠키? 세션? jwt? 정해야 진행이 있을 것 같습니다
  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@RequestBody LoginRequest req) {
    User user = userService.login(req);
    if(user == null) {
      return new ResponseEntity<>("로그인 실패", HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  // 로그아웃 요청



}
