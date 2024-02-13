package com.Ohsul.Ohsul.config;

import com.Ohsul.Ohsul.service.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class MyUserDetailsService implements UserDetailsService {

  private final UserService userService;
  public MyUserDetailsService(UserService userService){
    this.userService = userService;
  }
  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException{
    Optional<com.Ohsul.Ohsul.entity.User> findOne = userService.findOne(userId);
    com.Ohsul.Ohsul.entity.User user = findOne.orElseThrow(()->new UsernameNotFoundException("유저를 찾을 수 없습니다"));
    return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUserId())
            .password(user.getUserPw())
            .build();
  }
}
