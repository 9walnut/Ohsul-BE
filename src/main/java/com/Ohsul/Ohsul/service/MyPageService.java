package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.entity.UserEntity;
import com.Ohsul.Ohsul.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyPageService {
  private final UserRepository userRepository;
  @Autowired
  BCryptPasswordEncoder passwordEncoder;

  @Autowired
  public MyPageService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // 마이페이지 메인
  // 즐겨찾기 버튼 혼용 중
  public UserFavoriteDTO getUserProfile(String userId) {
    UserEntity userEntity = userRepository.findByUserId(userId)
            .orElseThrow(() -> new UsernameNotFoundException("유저가 없습니다 : " + userId));

    if (userEntity == null) {
      throw new UsernameNotFoundException("유저가 없습니다 : " + userId);
    }

    UserFavoriteDTO userProfile = new UserFavoriteDTO();
    userProfile.setUserId(userEntity.getUserId());
    userProfile.setUserName(userEntity.getUserName());
    userProfile.setUserNickname(userEntity.getUserNickname());
    userProfile.setFavorites(userEntity.getFavorites());

    return userProfile;
  }

  // 단순 데이터 전달 목적으로 Setter 사용
  public MyPageDTO getUserProfileInfo(String userId){
    UserEntity userEntity = userRepository.findByUserId(userId)
            .orElseThrow(() -> new UsernameNotFoundException("유저가 없습니다 : " + userId));

    if (userEntity == null) {
      throw new UsernameNotFoundException("유저가 없습니다 : " + userId);
    }

    MyPageDTO userProfileInfo = new MyPageDTO();
    userProfileInfo.setUserId(userEntity.getUserId());
    userProfileInfo.setUserName(userEntity.getUserName());
    userProfileInfo.setUserNickname(userEntity.getUserNickname());

    return userProfileInfo;
  }

  public void deleteUser(String userId) {
    UserEntity userEntity = userRepository.findByUserId(userId)
            .orElseThrow(() -> new UsernameNotFoundException("유저가 없습니다 : " + userId));
    userRepository.delete(userEntity);
  }

  public void updateUserProfile(String userId, MyPageDTO myPageDTO){
    UserEntity userEntity = userRepository.findByUserId(userId)
            .orElseThrow(()->new NoSuchElementException("유저가 없습니다"));

    // 받아온 DTO에서 값을 가져와서 업데이트
    userEntity.changeUserName(myPageDTO.getUserName());
    userEntity.changeUserNickname(myPageDTO.getUserNickname());
    userEntity.changeUserPw(passwordEncoder.encode(myPageDTO.getUserPw())); // 비밀번호 변경 시 암호화 필요

    userRepository.save(userEntity);
  }

  // 즐겨찾기
  public UserReviewDTO getUserReview(String userId){
    UserEntity userEntity = userRepository.findByUserId(userId)
            .orElseThrow(() -> new UsernameNotFoundException("유저가 없습니다 : " + userId));

    if (userEntity == null) {
      throw new UsernameNotFoundException("유저가 없습니다 : " + userId);
    }

    UserReviewDTO userReivew = new UserReviewDTO();
    userReivew.setUserId(userEntity.getUserId());
    userReivew.setUserName(userEntity.getUserName());
    userReivew.setUserNickname(userEntity.getUserNickname());

    return userReivew;
  }
}
