package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.entity.BarAlcoholEntity;
import com.Ohsul.Ohsul.entity.BarAlcoholKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarAlcoholRepository extends JpaRepository<BarAlcoholEntity, BarAlcoholKey> {
//    List<BarAlcoholEntity> findByReview_reviewId(Integer reviewId);

    List<BarAlcoholEntity> findAllByReview_reviewId(Integer reviewId);
    void deleteByReview_reviewId(Integer reviewId);
}
