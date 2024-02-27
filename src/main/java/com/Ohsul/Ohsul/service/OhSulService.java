package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.entity.*;
import com.Ohsul.Ohsul.repository.BarRepository;
import com.Ohsul.Ohsul.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OhSulService {
    @Autowired
     BarRepository barRepository;
    @Autowired
    ReviewRepository reviewRepository;

    public OhSulService(BarRepository barRepository, ReviewRepository reviewRepository) {

        this.barRepository = barRepository;
        this.reviewRepository = reviewRepository;
    }


    public List<BarListDTO> getBarScoreAndReviewInfo(List<BarSearchDTO> requests) {
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

    private BarListDTO convertEntityToDto(BarEntity barentity) {
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

        // 직접 쿼리 대신 평균 점수 계산
        List<ReviewEntity> reviews = reviewRepository.findAllByBar_BarId(barentity.getBarId());
        double avgScore = reviews.stream()
                .mapToDouble(ReviewEntity::getScore)
                .average()
                .orElse(Double.NaN); // 리뷰가 없는 경우 처리
        barListDTO.setBarAvgScore(avgScore);

        // 최근 리뷰 조회를 위해 수정된 부분
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
