package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.service.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

  private final RegisterService registerService;
  @Autowired
  public RegisterController(RegisterService registerService) {
    this.registerService = registerService;
  }
  // 회원가입 페이지
//  @GetMapping("/register")
//  public String disRegister(){
//    return "/register";
//  }

  // 회원가입 요청
  @PostMapping("")
  public ResponseEntity<?> registerUser(@RequestBody RegisterRequest req){
    registerService.registerUser(req);
    return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
  }

  // 아이디 중복 확인
  @PostMapping("/userIdCheck")
  public ResponseEntity<?> checkUserIdDuplicate(@RequestBody UserIdCheckRequest req) {
    boolean result = registerService.checkLoginIdDuplicate(req.getUserId());
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
  // 닉네임 중복 확인
  @PostMapping("/userNicknameCheck")
  public ResponseEntity<?> checkNicknameDuplicate(@RequestBody UserNicknameCheckRequest req) {
    boolean result = registerService.checkNicknameDuplicate(req.getUserNickname());
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
