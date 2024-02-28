package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.entity.*;
import com.Ohsul.Ohsul.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class BarEntityToDTOConverter {

  private final ReviewRepository reviewRepository;
  @Autowired
  public BarEntityToDTOConverter(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  public BarListDTO convertEntityToDto(BarEntity barentity) {
    BarListDTO barListDTO = new BarListDTO();
    barListDTO.setBarId(barentity.getBarId());
    barListDTO.setBarName(barentity.getBarName());
    barListDTO.setBarImg(barentity.getBarImg());

    barListDTO.setAlcoholTags(barentity.getBarAlcohols().stream()
            .map(barAlcoholEntity -> barAlcoholEntity.getAlcohol().getAlcoholId())
            .collect(Collectors.toList()));
    barListDTO.setMusicTags(barentity.getBarMusics().stream()
            .map(barMusicEntity -> barMusicEntity.getMusic().getMusicId())
            .collect(Collectors.toList()));
    barListDTO.setMoodTags(barentity.getBarMoods().stream()
            .map(barMoodEntity -> barMoodEntity.getMood().getMoodId())
            .collect(Collectors.toList()));

    List<ReviewEntity> reviews = reviewRepository.findAllByBar_BarId(barentity.getBarId());
    double avgScore = reviews.stream()
            .mapToDouble(ReviewEntity::getScore)
            .average()
            .orElse(Double.NaN); // 리뷰가 없는 경우 처리
    barListDTO.setBarAvgScore(avgScore);

    Page<ReviewEntity> reviewPage = reviewRepository.findAllByBar_BarIdOrderByDateDesc(barentity.getBarId(), PageRequest.of(0, 1));
    if (!reviewPage.isEmpty()) {
      ReviewEntity latestReview = reviewPage.getContent().get(0);
      BarRecentReviewDTO barReviewDTO = new BarRecentReviewDTO();
      barReviewDTO.setBarImg(latestReview.getReviewImg());
      barReviewDTO.setContent(latestReview.getContent());
      // 기타 필요한 필드 설정
      barListDTO.setBarRecentReviews(Collections.singletonList(barReviewDTO));
      barListDTO.setBarImg(latestReview.getReviewImg());
    }

    return barListDTO;
  }
}