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

    BarEtcEntity barEtc = barentity.getBarEtc();
    Map<String, Boolean> etcTags = new HashMap<>();
    etcTags.put("toilet", barEtc.getToilet());
    etcTags.put("parkingArea", barEtc.getParkingArea());
    etcTags.put("snack", barEtc.getSnack());
    barListDTO.setEtcTags(etcTags);

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
            .orElse(0.0); // 리뷰가 없는 경우 처리
    barListDTO.setBarAvgScore(avgScore);

    Page<ReviewEntity> reviewPage = reviewRepository.findAllByBar_BarIdOrderByDateDesc(barentity.getBarId(), PageRequest.of(0, 1));
    BarRecentReviewDTO barReviewDTO = new BarRecentReviewDTO();

    if (!reviewPage.isEmpty()) {
      ReviewEntity latestReview = reviewPage.getContent().get(0);
      barReviewDTO.setBarImg(latestReview.getReviewImg());
      barReviewDTO.setContent(latestReview.getContent());
    } else {
      barReviewDTO.setBarImg("https://ohsul.s3.ap-northeast-2.amazonaws.com/reviewImg/noimage.png");
      barReviewDTO.setContent("");
    }

// DTO 설정
    barListDTO.setBarRecentReviews(Collections.singletonList(barReviewDTO));
    barListDTO.setBarImg(barReviewDTO.getBarImg());

    return barListDTO;
  }
}
