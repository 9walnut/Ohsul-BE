package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.entity.UserEntity;
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

  public UserFavoriteDTO getUserProfile(Integer userNumber) {
    UserEntity userEntity = userRepository.findByUserNumber(userNumber)
            .orElseThrow(() -> new UsernameNotFoundException("유저가 없습니다 : " + userNumber));

    if (userEntity == null) {
      throw new UsernameNotFoundException("유저가 없습니다 : " + userNumber);
    }

    UserFavoriteDTO userProfile = new UserFavoriteDTO();
    userProfile.setUserId(userEntity.getUserId());
    userProfile.setUserName(userEntity.getUserName());
    userProfile.setUserNickname(userEntity.getUserNickname());
    userProfile.setFavorites(userEntity.getFavorites());

    return userProfile;
  }

  // 단순 데이터 전달 목적으로 Setter 사용
  public UserFavoriteDTO getUserProfileInfo(Integer userNumber){
    UserEntity userEntity = userRepository.findByUserNumber(userNumber)
            .orElseThrow(() -> new UsernameNotFoundException("유저가 없습니다 : " + userNumber));

    if (userEntity == null) {
      throw new UsernameNotFoundException("유저가 없습니다 : " + userNumber);
    }

    UserFavoriteDTO userProfileInfo = new UserFavoriteDTO();
    userProfileInfo.setUserId(userEntity.getUserId());
    userProfileInfo.setUserName(userEntity.getUserName());
    userProfileInfo.setUserNickname(userEntity.getUserNickname());

    return userProfileInfo;
  }

  public void deleteUser(Integer userNumber) {
    UserEntity userEntity = userRepository.findByUserNumber(userNumber)
            .orElseThrow(() -> new UsernameNotFoundException("유저가 없습니다 : " + userNumber));
    userRepository.delete(userEntity);
  }

  public void updateUserProfile(UserFavoriteDTO userFavoriteDTO){
    UserEntity userEntity = userRepository.findByUserNumber(userFavoriteDTO.getUserNumber())
            .orElseThrow(()->new NoSuchElementException("유저가 없습니다"));

    // 받아온 DTO에서 값을 가져와서 업데이트
    userEntity.changeUserName(userFavoriteDTO.getUserName());
    userEntity.changeUserNickname(userFavoriteDTO.getUserNickname());
    userEntity.changeUserPw(userFavoriteDTO.getUserPw()); // 비밀번호 변경 시 암호화 필요


    userRepository.save(userEntity);
  }
}