package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MyPageController {
  private final MyPageService myPageService;

  // 마이페이지 렌더링
  @GetMapping("")
  public ResponseEntity<?> getUserProfile(@RequestParam String userId) {
    UserProfileRequest userProfile = myPageService.getUserProfile(userId);
    return new ResponseEntity<>(userProfile, HttpStatus.OK);
  }

  // 마이페이지 내 정보 조회
  @GetMapping("/info")
  public ResponseEntity<?> getUserProfileInfo(@RequestParam String userId){
    UserProfileRequest userProfileInfo = myPageService.getUserProfileInfo(userId);
    return new ResponseEntity<>(userProfileInfo, HttpStatus.OK);
  }

  // 회원 탈퇴
  @DeleteMapping("/{userId}")
  public ResponseEntity<String> deleteUser(@PathVariable Integer userNumber) {
    myPageService.deleteUser(userNumber);
    return new ResponseEntity<>("회원 탈퇴 완료", HttpStatus.OK);
  }

  // 내 정보 수정
  @PatchMapping("/info")
  public void updateUserProfile(@RequestBody UserProfileRequest userProfileRequest){
    myPageService.updateUserProfile(userProfileRequest);
  }

  // 즐겨찾기

  // 작성한 리뷰

  // 리뷰 수정

  // 리뷰 삭제

  // 내 오술태그 조회

  // 태그 수정

}
