package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.dto.BarAvgDTO;
import com.Ohsul.Ohsul.dto.BarRecentReviewDTO;
import com.Ohsul.Ohsul.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
    // 특정 바 전체 리뷰 조회
    List<ReviewEntity> findAllByBar_BarId(Integer barId);
    List<ReviewEntity> findByUser_UserId(String userId);

    // 평균 4점이 넘는 바id 반환
    @Query(nativeQuery = true, value = "SELECT bar_id FROM review GROUP BY bar_id HAVING AVG(score) >= 4")
    List<Integer> findBarAByAvgScore();

    // 특정 바들에 대한 평균 점수
    @Query(nativeQuery = true, value = "SELECT bar_id, AVG(score) as avgScore FROM review WHERE bar_id IN (:barIds) GROUP BY bar_id")
    List<BarAvgDTO> findBarAvgScore(@Param("barIds") List<Integer> barIds);

    // 특정 바들에 대한 최근 리뷰 내용과 리뷰 이미지
    @Query(nativeQuery = true, value = "SELECT r1.bar_id, r1.review_img, r1.content FROM review r1 WHERE r1.date = (SELECT MAX(r2.date) FROM review r2 WHERE r1.bar_id = r2.bar_id) AND r1.bar_id IN (:barIds)")
    List<BarRecentReviewDTO> findReview(@Param("barIds") List<Integer> barIds);

};

