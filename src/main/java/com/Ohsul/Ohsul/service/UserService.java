package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.*;

import com.Ohsul.Ohsul.entity.User;
import com.Ohsul.Ohsul.repository.*;
import jakarta.transaction.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
  private UserRepository userRepository;
  // spring security 를 사용한 로그인 구현
  private BCryptPasswordEncoder encoder;


  @Autowired
  public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  public  Optional<com.Ohsul.Ohsul.entity.User> findOne(String userId){
    return userRepository.findByUserId(userId);
  }

  // 로그인
  public User login(LoginRequest req){
    Optional<com.Ohsul.Ohsul.entity.User> optionalUser = userRepository.findByUserId(req.getUserId());

    // 일치하는 user 없을 시 null
    if (optionalUser.isEmpty()){
      return null;
    }
    com.Ohsul.Ohsul.entity.User user = optionalUser.get();

    if(!user.getUserPw().equals(req.getUserPw())){
      return null;
    }
    return user;
  }

//  @Override
//  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException{
//    User user = userRepository.findByUserId(userId);
//    if(user == null){
//      throw new UsernameNotFoundException(userId);
//    }
//    return User.builder()
//            .userId(user.getUserId())
//            .userPw(user.getUserPw())
//            .build();
//  }
}
