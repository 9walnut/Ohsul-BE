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
@RequestMapping("/api/mypage")
public class MyPageController {

  private final MyPageService myPageService;

  // 마이페이지 렌더링
  @GetMapping("")
  public ResponseEntity<?> getUserProfile(@AuthenticationPrincipal String userId) {
    try {
      UserFavoriteDTO userProfile = myPageService.getUserProfile(userId);
      return new ResponseEntity<>(userProfile, HttpStatus.OK);
    } catch (Exception e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  // 마이페이지 내 정보 조회
  @GetMapping("/info")
  public ResponseEntity<?> getUserProfileInfo(@AuthenticationPrincipal String userId){
    try {
      MyPageDTO userProfileInfo = myPageService.getUserProfileInfo(userId);
      return new ResponseEntity<>(userProfileInfo, HttpStatus.OK);
    } catch (Exception e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  // 회원 탈퇴
  @DeleteMapping("")
  public ResponseEntity<String> deleteUser(@AuthenticationPrincipal String userId) {
    try {
      myPageService.deleteUser(userId);
      return new ResponseEntity<>("회원 탈퇴 완료", HttpStatus.OK);
    } catch (Exception e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  // 내 정보 수정
  @PatchMapping("/info")
  public void updateUserProfile(@AuthenticationPrincipal String userId, @RequestBody MyPageDTO myPageDTO){
    myPageService.updateUserProfile(userId, myPageDTO);
  }

  // 즐겨찾기 버튼
  @GetMapping("/favorite")
  public ResponseEntity<?> getFavorite(@AuthenticationPrincipal String userId){
    try {
      UserFavoriteDTO userFavorite = myPageService.getUserProfile(userId);
      return new ResponseEntity<>(userFavorite, HttpStatus.OK);
    } catch (Exception e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  // 작성한 리뷰
  @GetMapping("/myReview")
  public ResponseEntity<?> getMyReview(@AuthenticationPrincipal String userId){
    try {
      UserReviewDTO userReview = myPageService.getUserReview(userId);
      return new ResponseEntity<>(userReview, HttpStatus.OK);
    } catch (Exception e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
