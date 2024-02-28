package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.entity.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
    // 특정 바 전체 리뷰 조회
    List<ReviewEntity> findAllByBar_BarId(Integer barId);
    List<ReviewEntity> findByUser_UserId(String userId);

    // 평균 4점이 넘는 바id 반환
    @Query(nativeQuery = true, value = "SELECT bar_id, AVG(score) FROM review GROUP BY bar_id HAVING AVG(score) >= 4")
    List<Integer> findBarIdByAvgScore();

    // 최신 리뷰 조회
    Page<ReviewEntity> findAllByBar_BarIdOrderByDateDesc(Integer barId, Pageable pageable);
};

