package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.domain.Review;
import com.Ohsul.Ohsul.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
    // 특정 바 전체 리뷰 조회
    List<ReviewEntity> findAllByBar_BarId(Integer barId);
    List<ReviewEntity> findByUser_UserId(String userId);
};

