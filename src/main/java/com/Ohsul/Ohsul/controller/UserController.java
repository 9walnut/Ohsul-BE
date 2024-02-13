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
@RequestMapping("/api/login")
public class UserController {
  private final UserService userService;

  // 로그인 페이지
  @GetMapping("/")
  public String getLogin(){
    return "GET /login";
  }
  // 로그인 요청
  // 쿠키? 세션? jwt? 정해야 진행이 있을 것 같습니다
  @PostMapping("/")
  public ResponseEntity<?> loginUser(@RequestBody LoginRequest req) {
    try{
      User user = userService.login(req);

      if(user == null) {
        return new ResponseEntity<>("로그인 실패", HttpStatus.UNAUTHORIZED);
      }
      return new ResponseEntity<>(user, HttpStatus.OK);
    } catch (Exception e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

  // 로그아웃 요청



}
