package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.dto.BarReviewDTO;
import com.Ohsul.Ohsul.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/ohsul")
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

    // 리뷰 등록 (이미지 데이터까지 한 번에)
    @PostMapping("/{barId}/review")
    public ResponseEntity<?> createReview(@PathVariable Integer barId, @RequestParam(required = false) MultipartFile reviewImg,
                                          @RequestPart BarReviewDTO barReviewDTO,
                                          @AuthenticationPrincipal String userId) {
        try {
            return ResponseEntity.ok(reviewService.createReview(barId, reviewImg, barReviewDTO, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 리뷰 등록(사진)
//    @PostMapping(value = "/{barId}/review/reviewImg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public String createReviewImg(@RequestParam("reviewImg") MultipartFile reviewImg) {
//            return reviewService.createReviewImg(reviewImg);
//    }

    // 리뷰 > '수정' 클릭 시 (원래 리뷰 내용 반환)
    @PostMapping("/{barId}/review/{reviewId}")
    public ResponseEntity<?> getOriginReview(@PathVariable Integer reviewId) {
        try {
            return ResponseEntity.ok().body(reviewService.getBarReview(reviewId));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 리뷰 > 수정하기 (이미지 데이터까지 한 번에)
    @PatchMapping("/{barId}/review/{reviewId}")
    public ResponseEntity<?> editReview(@PathVariable Integer barId, @PathVariable Integer reviewId,
                                        @RequestPart MultipartFile reviewImg,
                                        @RequestPart BarReviewDTO barReviewDTO,
                                        @AuthenticationPrincipal String userId) {
        try {
            Boolean result = reviewService.editReview(barId, reviewId, reviewImg, barReviewDTO, userId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // (비회원) 리뷰 수정/삭제 (비번 일치 여부 확인)
    @PostMapping("/{barId}/review/{reviewId}/userCheck")
    public ResponseEntity<?> userCheck(@PathVariable Integer barId, @PathVariable Integer reviewId, @RequestBody BarReviewDTO barReviewDTO) {
        try {
            Boolean result = reviewService.userCheck(reviewId, barReviewDTO);
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
