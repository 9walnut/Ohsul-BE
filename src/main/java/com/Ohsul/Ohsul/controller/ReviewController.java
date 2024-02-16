package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.dto.BarReviewDTO;
import com.Ohsul.Ohsul.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ohsul")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    // 특정 바 전체 리뷰 조회
    @GetMapping("/{barId}/review")
    public ResponseEntity<?> barReviewAll(@PathVariable Integer barId) {
        try {
            return ResponseEntity.ok().body(reviewService.getBarReviewAll(barId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 리뷰 등록
    @PostMapping("/{barId}/review")
    public ResponseEntity<?> createReview(@PathVariable Integer barId, @RequestBody BarReviewDTO barReviewDTO, @AuthenticationPrincipal Integer userNumber) {
        try {
            Boolean result = reviewService.createReview(barId, barReviewDTO, userNumber);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 리뷰 수정 시 비회원 닉네임, 비번 일치 여부 확인
    @PostMapping("/{barId}/review/{reviewId}/editCheck")
    public ResponseEntity<?> editUserCheck(@PathVariable Integer barId, @PathVariable Integer reviewId, @RequestBody BarReviewDTO barReviewDTO) {
        try {
            Boolean result = reviewService.editUserCheck(reviewId, barReviewDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 리뷰 수정
    @PatchMapping("/{barId}/review/{reviewId}")
    public ResponseEntity<?> editReview(@PathVariable Integer barId, @PathVariable Integer reviewId, @RequestBody BarReviewDTO barReviewDTO, @AuthenticationPrincipal Integer userNumber) {
        try {
            Boolean result = reviewService.editReview(barId, reviewId, barReviewDTO, userNumber);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 리뷰 삭제
    @DeleteMapping("/{barId}/review/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer reviewId) {
        try {
            Boolean result = reviewService.deleteReview(reviewId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 리뷰 상세 조회
    @GetMapping("/{barId}/review/{reviewId}")
        public ResponseEntity<?> getBarReview(@PathVariable Integer reviewId) {
        try {
            return ResponseEntity.ok().body(reviewService.getBarReview(reviewId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
