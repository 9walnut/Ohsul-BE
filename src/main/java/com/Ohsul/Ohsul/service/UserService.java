package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.entity.*;
import com.Ohsul.Ohsul.repository.*;
import jakarta.transaction.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
  private UserRepository userRepository;
  // spring security 를 사용한 로그인 구현
  private final BCryptPasswordEncoder encoder;

  // userId 중복체크
  // 종복되면 true return
  public boolean checkLoginIdDuplicate(String userId){
    return userRepository.existsByUserId(userId);
  }

  // 닉네임 중복체크
  public boolean checkNicknameDuplicate(String userNickname){
    return userRepository.existsByNickname(userNickname);
  }

  // 회원가입 - 암호화
  public void registerUser(RegisterRequest req){
    userRepository.save(req.toEntity(encoder.encode(req.getUserPw())));
  }

  // 로그인
  public User login(LoginRequest req){
    Optional<User> optionalUser = userRepository.findByUserId(req.getUserId());

    // 일치하는 user 없을 시 null
    if (optionalUser.isEmpty()){
      return null;
    }
    User user = optionalUser.get();

    if(!user.getUserPw().equals(req.getUserPw())){
      return null;
    }
    return user;
  }
}
