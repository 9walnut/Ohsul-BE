package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.BarAvgDTO;
import com.Ohsul.Ohsul.dto.BarListDTO;
import com.Ohsul.Ohsul.dto.BarRecentReviewDTO;
import com.Ohsul.Ohsul.entity.BarEntity;
import com.Ohsul.Ohsul.repository.BarRepository;
import com.Ohsul.Ohsul.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OhSulService {
    @Autowired
    BarRepository barRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public ResponseEntity<?> getBarScoreAndReviewInfo(List<String> telephones, List<String> barNames) {
        List<BarEntity> bars = barRepository.findAllByTelephoneIn(telephones);

        if (bars.isEmpty() && !barNames.isEmpty()) {
            bars = barRepository.findByBarNameIn(barNames);
        }

        List<BarListDTO> barListDTOS = bars.stream().map(this::convertEntityToDto).collect(Collectors.toList());

        List<Integer> barIds = bars.stream().map(BarEntity::getBarId).collect(Collectors.toList()); // 검색된 바id

        List<BarAvgDTO> barAvgScores = reviewRepository.findBarAvgScore(barIds); // 특정 바에 대한 평균 점수
        List<BarRecentReviewDTO> barReview = reviewRepository.findReview(barIds); // 특정 바의 최근 리뷰 내용과 리뷰 이미지

        Map<String, Object> response = new HashMap<>();
        response.put("barInfo", barListDTOS);
        response.put("barAvgScore", barAvgScores);
        response.put("barReview", barReview);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private BarListDTO convertEntityToDto(BarEntity barentity) {
        BarListDTO barListDTO = new BarListDTO();
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

        return barListDTO;
    }

}
