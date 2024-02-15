package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.domain.*;
import com.Ohsul.Ohsul.dto.*;

import com.Ohsul.Ohsul.entity.UserEntity;
import com.Ohsul.Ohsul.repository.*;
import jakarta.transaction.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
  private UserRepository userRepository;
  @Autowired
  BCryptPasswordEncoder passwordEncoder;

//  public  Optional<UserEntity> findOne(String userId){
//    return userRepository.findByUserId(userId);
//  }

  // 로그인
  public UserEntity login(String userId, String userPw){
    UserEntity searchUser = userRepository.findByUserId(userId);

    // 일치하는 user 없을 시 null
    if (searchUser != null && passwordEncoder.matches(userPw, searchUser.getUserPw())) {

      return searchUser;
    }
    return null;
  }
 // 회원가입
  public UserEntity registerUser(UserEntity userEntity){
    if(userEntity == null){
      throw new RuntimeException("entity null");
    }
    // 중복 아이디 불가
    String id = userEntity.getUserId();

    if(userRepository.existsByUserId(userEntity.getUserId())){
      throw new RuntimeException("이미 존재하는 아이디입니다");
    }
    return userRepository.save(userEntity);
  }

  // 아이디 중복 체크
  public boolean checkLoginIdDuplicate(String userId){
    return userRepository.existsByUserId(userId);
  }

  // 닉네임 중복체크
  public boolean checkNicknameDuplicate(String userNickname){
    return userRepository.existsByUserNickname(userNickname);
  }


}
