package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.entity.BarMoodEntity;
import com.Ohsul.Ohsul.entity.BarMoodKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarMoodRepository extends JpaRepository<BarMoodEntity, BarMoodKey> {
    List<BarMoodEntity> findByReviewId(Integer reviewId);

    void deleteByReviewId(Integer reviewId);

}
