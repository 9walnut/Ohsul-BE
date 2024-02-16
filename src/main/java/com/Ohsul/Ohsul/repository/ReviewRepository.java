package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
    // 특정 바 전체 리뷰 조회
    List<ReviewEntity> findAllById(Integer barId);
};

