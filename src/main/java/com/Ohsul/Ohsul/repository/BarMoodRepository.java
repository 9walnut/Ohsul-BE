package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.entity.BarMoodEntity;
import com.Ohsul.Ohsul.entity.BarMoodKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarMoodRepository extends JpaRepository<BarMoodEntity, BarMoodKey> {
     List<BarMoodEntity> findAllByReview_reviewId(Integer reviewId);
    void deleteByReview_reviewId(Integer reviewId);
    List<BarMoodEntity> findAllByBar_BarId(Integer barId);
}
