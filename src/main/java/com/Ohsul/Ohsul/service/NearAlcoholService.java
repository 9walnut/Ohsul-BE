package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.entity.*;
import com.Ohsul.Ohsul.repository.*;
import com.fasterxml.jackson.databind.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.*;

@Service
public class NearAlcoholService {

  @Autowired
  BarRepository barRepository;
  @Autowired
  ReviewRepository reviewRepository;

  public NearAlcoholService(BarRepository barRepository, ReviewRepository reviewRepository) {

    this.barRepository = barRepository;
    this.reviewRepository = reviewRepository;
  }

  public List<BarListDTO> findBarsByRequestList(List<BarSearchDTO> requests) {
    Set<BarEntity> barSet = new HashSet<>();
    for (BarSearchDTO request : requests) {
      BarEntity bar = null;

      if (request.getTelephone() != null && !request.getTelephone().isBlank()) {
        bar = barRepository.findByTelephone(request.getTelephone());
      }

      if (bar == null && request.getBarName() != null && !request.getBarName().isBlank()) {
        bar = barRepository.findByBarName(request.getBarName());
      }

      if (bar == null) {
        bar = new BarEntity();
        bar.setTelephone(request.getTelephone());
        bar.setBarName(request.getBarName());
        bar.setRoadAddress(request.getRoadAddress());
        barRepository.save(bar);
      }

      barSet.add(bar);
    }
    return barSet.stream().map(this::convertEntityToDto).collect(Collectors.toList());
  }

  private BarListDTO convertEntityToDto(BarEntity barEntity) {
    BarListDTO barListDTO = new BarListDTO();
    barListDTO.setBarId(barEntity.getBarId());
    barListDTO.setBarName(barEntity.getBarName());
    barListDTO.setBarImg(barEntity.getBarImg());

    barListDTO.setAlcoholTags(barEntity.getBarAlcohols().stream()
            .map(barAlcoholEntity -> barAlcoholEntity.getAlcohol().getAlcoholId())
            .collect(Collectors.toList()));
    barListDTO.setMusicTags(barEntity.getBarMusics().stream()
            .map(barMusicEntity -> barMusicEntity.getMusic().getMusicId())
            .collect(Collectors.toList()));
    barListDTO.setMoodTags(barEntity.getBarMoods().stream()
            .map(barMoodEntity -> barMoodEntity.getMood().getMoodId())
            .collect(Collectors.toList()));

    // 직접 쿼리 대신 평균 점수 계산
    List<ReviewEntity> reviews = reviewRepository.findAllByBar_BarId(barEntity.getBarId());
    double avgScore = reviews.stream()
            .mapToDouble(ReviewEntity::getScore)
            .average()
            .orElse(Double.NaN); // 리뷰가 없는 경우 처리
    barListDTO.setBarAvgScore(avgScore);

    // 최근 리뷰 조회를 위해 수정된 부분
    Page<ReviewEntity> reviewPage = reviewRepository.findAllByBar_BarIdOrderByDateDesc(barEntity.getBarId(), PageRequest.of(0, 1));
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
