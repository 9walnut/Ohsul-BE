package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.service.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

  private final MyPageService myPageService;

  // 마이페이지 렌더링
  @GetMapping("")
  public ResponseEntity<?> getUserProfile(@AuthenticationPrincipal String userId) {
    UserFavoriteDTO userProfile = myPageService.getUserProfile(userId);
    return new ResponseEntity<>(userProfile, HttpStatus.OK);
  }

  // 마이페이지 내 정보 조회
  @GetMapping("/info")
  public ResponseEntity<?> getUserProfileInfo(@RequestParam Integer userNumber){
    UserFavoriteDTO userProfileInfo = myPageService.getUserProfileInfo(userNumber);
    return new ResponseEntity<>(userProfileInfo, HttpStatus.OK);
  }

  // 회원 탈퇴
  @DeleteMapping("/")
  public ResponseEntity<String> deleteUser(@AuthenticationPrincipal String userId) {
    myPageService.deleteUser(userId);
    return new ResponseEntity<>("회원 탈퇴 완료", HttpStatus.OK);
  }

  // 내 정보 수정
  @PatchMapping("/info")
  public void updateUserProfile(@RequestBody UserFavoriteDTO userFavoriteDTO){
    myPageService.updateUserProfile(userFavoriteDTO);
  }

  // 즐겨찾기 버튼
  @GetMapping("/favorite")
  public ResponseEntity<?> getFavorite(@AuthenticationPrincipal String userId){
    UserFavoriteDTO userFavorite = myPageService.getUserProfile(userId);
    return new ResponseEntity<>(userFavorite, HttpStatus.OK);
  }

  // 작성한 리뷰
  @GetMapping("/myReview")
  public ResponseEntity<?> getMyReview(@RequestParam Integer userNumber){
    UserReviewDTO userReview = myPageService.getUserReview(userNumber);
    return new ResponseEntity<>(userReview, HttpStatus.OK);
  }

  // 리뷰 수정

  // 리뷰 삭제

  // 내 오술태그 조회

  // 태그 수정

}
