package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.entity.*;
import com.Ohsul.Ohsul.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.*;

@Service
public class MyPageService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  ReviewRepository reviewRepository;
  @Autowired
  FavoriteRepository favoriteRepository;
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
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userId));

    // 사용자가 즐겨찾기한 항목 조회
    List<FavoriteEntity> favorites = favoriteRepository.findByUser(userEntity);

    List<FavoriteDTO> favoriteDTOs = favorites.stream().map(favorite -> {
      BarEntity bar = favorite.getBar();
      FavoriteDTO dto = new FavoriteDTO();
      return dto;
    }).collect(Collectors.toList());

    UserFavoriteDTO userProfile = new UserFavoriteDTO();
    userProfile.setUserNumber(userEntity.getUserNumber());
    userProfile.setUserId(userEntity.getUserId());
    userProfile.setUserName(userEntity.getUserName());
    userProfile.setUserPw(userEntity.getUserPw());
    userProfile.setUserNickname(userEntity.getUserNickname());
    userProfile.setFavorites(favoriteDTOs);

    return userProfile;
  }

  // 마이페이지 내 정보 조회
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

  // 회원 탈퇴
  public void deleteUser(String userId) {
    UserEntity userEntity = userRepository.findByUserId(userId)
            .orElseThrow(() -> new UsernameNotFoundException("유저가 없습니다 : " + userId));
    userRepository.delete(userEntity);
  }

  // 내 정보 수정
  public void updateUserProfile(String userId, MyPageDTO myPageDTO){
    UserEntity userEntity = userRepository.findByUserId(userId)
            .orElseThrow(()->new NoSuchElementException("유저가 없습니다"));

    // 받아온 DTO에서 값을 가져와서 업데이트
    userEntity.changeUserName(myPageDTO.getUserName());
    userEntity.changeUserNickname(myPageDTO.getUserNickname());
    userEntity.changeUserPw(passwordEncoder.encode(myPageDTO.getUserPw())); // 비밀번호 변경 시 암호화 필요

    userRepository.save(userEntity);
  }

  // 작성한 리뷰
  public UserReviewDTO getUserReview(String userId) {
    // 유저 정보 조회
    UserEntity userEntity = userRepository.findByUserId(userId)
            .orElseThrow(() -> new UsernameNotFoundException("유저가 없습니다 : " + userId));

    // 유저가 작성한 리뷰들 조회
    List<ReviewEntity> reviews = reviewRepository.findByUser_UserId(userId);

    // UserReviewDTO 생성 및 유저 정보 설정
    UserReviewDTO userReview = new UserReviewDTO();
    userReview.setUserId(userEntity.getUserId());
    userReview.setUserName(userEntity.getUserName());
    userReview.setUserNickname(userEntity.getUserNickname());

    List<ReviewDTO> reviewDTOs = reviews.stream().map(review -> {
      ReviewDTO reviewDTO = new ReviewDTO();
      reviewDTO.setReviewId(review.getReviewId());
      reviewDTO.setContent(review.getContent());
      return reviewDTO;
    }).collect(Collectors.toList());

    userReview.setReviews(reviewDTOs); // UserReviewDTO에 리뷰 리스트 설정
    return userReview;
  }
}
