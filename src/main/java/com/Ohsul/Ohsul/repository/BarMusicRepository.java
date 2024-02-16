package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.entity.BarMusicEntity;
import com.Ohsul.Ohsul.entity.BarMusicKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarMusicRepository extends JpaRepository<BarMusicEntity, BarMusicKey> {
//    List<BarMusicEntity> findByReview_reviewId(Integer reviewId);

    void deleteByReview_reviewId(Integer reviewId);

}
