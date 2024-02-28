package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.BarListDTO;
import com.Ohsul.Ohsul.dto.BarRecentReviewDTO;
import com.Ohsul.Ohsul.entity.BarEntity;
import com.Ohsul.Ohsul.entity.ReviewEntity;
import com.Ohsul.Ohsul.repository.BarRepository;
import com.Ohsul.Ohsul.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MainAlcoholService {
    @Autowired
    BarRepository barRepository;

    @Autowired
    ReviewRepository reviewRepository;

    // 현재 주소 정보(구 단위)를 포함하는 술집 정보, 최근 리뷰 반환
    public List<BarListDTO> getAllBarByAddress(String curAddress) {
            List<BarEntity> barsByAddress = barRepository.findByRoadAddressLike("%" + curAddress + "%");

            return barsByAddress.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    // 평균 score가 4점 이상인 barId 정보, 평균 점수 반환
    public List<BarListDTO> getAllBarByScore() {
        // 평균 점수가 4점이 넘는 barId
        List<Integer> barIds = reviewRepository.findBarIdByAvgScore();
        List<BarEntity> bars = barRepository.findAllByBarIdIn(barIds);

        return bars.stream().map(this::convertEntityToDto).collect(Collectors.toList());
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
