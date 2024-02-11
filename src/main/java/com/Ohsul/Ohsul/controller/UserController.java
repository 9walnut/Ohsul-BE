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


  // 회원가입 페이지
//  @GetMapping("/register")
//  public String disRegister(){
//    return "/register";
//  }

  // 회원가입 요청
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody RegisterRequest req){
    userService.registerUser(req);
    return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
  }

  // 아이디 중복 확인
  @PostMapping("/register/userIdCheck")
  public ResponseEntity<?> checkUserIdDuplicate(@RequestBody UserIdCheckRequest req) {
    boolean result = userService.checkLoginIdDuplicate(req.getUserId());
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
  // 닉네임 중복 확인
  @PostMapping("/register/userNicknameCheck")
  public ResponseEntity<?> checkNicknameDuplicate(@RequestBody UserNicknameCheckRequest req) {
    boolean result = userService.checkNicknameDuplicate(req.getUserNickname());
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
