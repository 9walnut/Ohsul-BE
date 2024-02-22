package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.domain.Review;
import com.Ohsul.Ohsul.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
    // 특정 바 전체 리뷰 조회
    List<ReviewEntity> findAllByBar_BarId(Integer barId);
    List<ReviewEntity> findByUser_UserId(String userId);

    @Query(nativeQuery = true, value = "SELECT bar_id FROM review GROUP BY bar_id HAVING AVG(score) >= 4")
    List<Integer> findBarAByAvgScore();
};

