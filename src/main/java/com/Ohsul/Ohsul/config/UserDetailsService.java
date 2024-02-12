//package com.Ohsul.Ohsul.config;
//
//import com.Ohsul.Ohsul.service.*;
//import com.Ohsul.Ohsul.domain.User;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.stereotype.*;
//
//import java.util.*;
//
//@Component
//public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
//  private final UserService userService;
//  public UserDetailsService(UserService userService){
//    this.userService = userService;
//  }
//  @Override
//  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException{
//    Optional<User> findOne = userService.findOne(userId);
//    User user = findOne.orElseThrow(()->new UsernameNotFoundException("유저를 찾을 수 없습니다"));
//    return User.builder()
//            .username(user.getUsername())
//            .password(user.getPassword())
//            .build()
//  }
//}
