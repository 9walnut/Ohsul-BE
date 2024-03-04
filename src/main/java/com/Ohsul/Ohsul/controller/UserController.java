package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.entity.*;
import com.Ohsul.Ohsul.repository.*;
import com.Ohsul.Ohsul.service.*;
import jakarta.servlet.http.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserController {
  @Autowired
  UserService userService;

  @Autowired
  FavoriteRepository favoriteRepository;
  @Autowired
  BCryptPasswordEncoder passwordEncoder;

  // 로그인 요청
  @PostMapping("/login")
  public ResponseEntity<?> loginUser(HttpSession session, HttpServletResponse response ,@RequestBody UserDTO userDTO) {
    log.warn("post login");
    try{
      UserEntity user = userService.login(userDTO.getUserId(), userDTO.getUserPw());

      if(user == null) {
        throw new RuntimeException("login failed");
      }

      // 즐겨찾기 목록 조회
      List<FavoriteEntity> favorites = favoriteRepository.findByUser_UserId(user.getUserId());
      List<FavoriteDTO> favoriteDTOs = favorites.stream()
              .map(favorite -> {
                FavoriteDTO favoriteDTO = new FavoriteDTO();
                favoriteDTO.setBar(favorite.getBar());
                return favoriteDTO;
              })
              .collect(Collectors.toList());

      UserDTO responseUserDTO = UserDTO.builder()
              .userId(user.getUserId())
              .userNumber(user.getUserNumber())
              .userName(user.getUserName())
              .userNickname(user.getUserNickname())
              .favorites(favoriteDTOs)
              .build();
       session.setAttribute("userId", user.getUserId());
       session.setMaxInactiveInterval(3600);
       Cookie customCookie = new Cookie("userLoggedIn", user.getUserId());
       customCookie.setHttpOnly(true);
       customCookie.setPath("/");
       customCookie.setSecure(true);

       String cookieString = String.format("%s=%s; Path=%s; HttpOnly; SameSite=Lax", customCookie.getName(), customCookie.getValue(), customCookie.getPath());
       response.addHeader("Set-Cookie", cookieString);

      return ResponseEntity.ok().body(responseUserDTO);
    } catch (Exception e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  // 회원가입 요청
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
    try{
      UserEntity user = UserEntity.builder()
              .userId(userDTO.getUserId())
              .userPw(passwordEncoder.encode(userDTO.getUserPw()))
              .userName(userDTO.getUserName())
              .userNickname(userDTO.getUserNickname())
              .build();
      UserEntity responseUser = userService.registerUser(user);
      UserDTO responseuserDTO = UserDTO.builder()
              .userId(responseUser.getUserId())
              .userName(responseUser.getUserName())
              .userNickname(responseUser.getUserNickname())
              .userNumber(responseUser.getUserNumber())
              .build();
      return ResponseEntity.ok().body(responseuserDTO);
    } catch (Exception e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
  // 아이디 중복 확인
  @PostMapping("/register/userIdCheck")
  public ResponseEntity<?> checkUserIdDuplicate(@RequestBody UserIdCheckDTO req) {
    try {
      boolean result = userService.checkLoginIdDuplicate(req.getUserId());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  // 닉네임 중복 확인
  @PostMapping("/register/userNicknameCheck")
  public ResponseEntity<?> checkNicknameDuplicate(@RequestBody UserNicknameCheckDTO req) {
    try {
      boolean result = userService.checkNicknameDuplicate(req.getUserNickname());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
