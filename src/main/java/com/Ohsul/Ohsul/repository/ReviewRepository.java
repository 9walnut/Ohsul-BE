package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
    List<ReviewEntity> findByReviewId(Integer reviewId);
    List<ReviewEntity> findByUser_UserId(String userId);
}
