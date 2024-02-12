package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.repository.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterService {
  private final UserRepository userRepository;
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

}
