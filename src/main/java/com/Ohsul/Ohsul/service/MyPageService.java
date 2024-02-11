package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.entity.User;
import com.Ohsul.Ohsul.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;

import java.util.*;

public class MyPageService {
  private final UserRepository userRepository;

  @Autowired
  public MyPageService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserProfileRequest getUserProfile(String userId) {
    User user = userRepository.findByUserId(userId)
            .orElseThrow(() -> new UsernameNotFoundException("유저가 없습니다 : " + userId));

    if (user == null) {
      throw new UsernameNotFoundException("유저가 없습니다 : " + userId);
    }

    UserProfileRequest userProfile = new UserProfileRequest();
    userProfile.setUserId(user.getUserId());
    userProfile.setUserName(user.getUserName());
    userProfile.setUserNickname(user.getUserNickname());
    userProfile.setFavorites(user.getFavorites());

    return userProfile;
  }

  // 단순 데이터 전달 목적으로 Setter 사용
  public UserProfileRequest getUserProfileInfo(String userId){
    User user = userRepository.findByUserId(userId)
            .orElseThrow(() -> new UsernameNotFoundException("유저가 없습니다 : " + userId));

    if (user == null) {
      throw new UsernameNotFoundException("유저가 없습니다 : " + userId);
    }

    UserProfileRequest userProfileInfo = new UserProfileRequest();
    userProfileInfo.setUserId(user.getUserId());
    userProfileInfo.setUserName(user.getUserName());
    userProfileInfo.setUserNickname(user.getUserNickname());

    return userProfileInfo;
  }

  public void deleteUser(Integer userNumber) {
    User user = userRepository.findByUserNumber(userNumber)
            .orElseThrow(() -> new UsernameNotFoundException("유저가 없습니다 : " + userNumber));
    userRepository.delete(user);
  }

  public void updateUserProfile(UserProfileRequest userProfileRequest){
    User user = userRepository.findByUserId(userProfileRequest.getUserId())
            .orElseThrow(()->new NoSuchElementException("유저가 없습니다"));
    User updateUser = User.builder().userPw(user.getUserPw()).userName(user.getUserName()).userNickname(user.getUserNickname())
            .build();
    userRepository.save(updateUser);
  }
}
