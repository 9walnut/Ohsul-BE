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
            .orElseThrow(() -> new UsernameNotFoundException("유저가 없습니다" + userId));

    List<FavoriteEntity> favorites = favoriteRepository.findByUser_UserId(userId);

    List<FavoriteDTO> favoriteDTOs = favorites.stream().map(favorite -> {
      BarEntity bar = favorite.getBar();
      List<ReviewEntity> reviews = reviewRepository.findAllByBar_BarId(bar.getBarId());
      double avgScore = reviews.stream()
              .mapToDouble(ReviewEntity::getScore)
              .average()
              .orElse(Double.NaN);
      FavoriteDTO dto = new FavoriteDTO();
      dto.setBar(bar);
      dto.setAvgScore(avgScore);
      return dto;
    }).collect(Collectors.toList());

    UserFavoriteDTO userProfile = new UserFavoriteDTO();
    userProfile.setUserNumber(userEntity.getUserNumber());
    userProfile.setUserId(userEntity.getUserId());
    userProfile.setUserName(userEntity.getUserName());
    userProfile.setUserNickname(userEntity.getUserNickname());
    userProfile.setFavorites(favoriteDTOs);

    return userProfile;
  }

  // 마이페이지 내 정보 조회
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

    userEntity.changeUserName(myPageDTO.getUserName());
    userEntity.changeUserNickname(myPageDTO.getUserNickname());
    userRepository.save(userEntity);
  }
  
  // 비밀번호 확인
  public boolean checkUserPw(String userId, UserPwCheckDTO userPwCheckDTO){
    UserEntity userEntity = userRepository.findByUserId(userId)
            .orElseThrow(()->new NoSuchElementException("유저가 없습니다"));
    return passwordEncoder.matches(userPwCheckDTO.getUserPw(), userEntity.getUserPw());
  }

  //  비밀번호 수정
  public void updateUserPassword(String userId, UserPwUpdateDTO userPwUpdateDTO){
    UserEntity userEntity = userRepository.findByUserId(userId)
            .orElseThrow(()->new NoSuchElementException("유저가 없습니다"));
    userEntity.changeUserPw(passwordEncoder.encode(userPwUpdateDTO.getUserPw()));
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
      reviewDTO.setBarId(review.getBar().getBarId());
      reviewDTO.setReviewId(review.getReviewId());
      reviewDTO.setContent(review.getContent());
      reviewDTO.setReviewImg(review.getReviewImg());
      reviewDTO.setDate(review.getDate());
      reviewDTO.setScore(review.getScore());

      BarEntity bar = review.getBar();

      List<Integer> alcoholTags = bar.getBarAlcohols().stream()
              .map(BarAlcoholEntity::getAlcoholId)
              .collect(Collectors.toList());
      reviewDTO.setAlcoholTags(alcoholTags);

      List<Integer> musicTags = bar.getBarMusics().stream()
              .map(BarMusicEntity::getMusicId)
              .collect(Collectors.toList());
      reviewDTO.setMusicTags(musicTags);

      List<Integer> moodTags = bar.getBarMoods().stream()
              .map(BarMoodEntity::getMoodId)
              .collect(Collectors.toList());
      reviewDTO.setMoodTags(moodTags);

      return reviewDTO;
    }).collect(Collectors.toList());

    userReview.setReviews(reviewDTOs);
    return userReview;
  }
}
