package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.BarListDTO;
import com.Ohsul.Ohsul.entity.BarEntity;
import com.Ohsul.Ohsul.entity.ReviewEntity;
import com.Ohsul.Ohsul.repository.BarRepository;
import com.Ohsul.Ohsul.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainAlcoholService {
    @Autowired
    BarRepository barRepository;

    @Autowired
    ReviewRepository reviewRepository;

    // 현재 주소 정보(구 단위)를 포함하는 술집 정보 모두 반환
    public List<BarListDTO> getAllBarByAddress(String curAddress) {
            List<BarEntity> barsByAddress = barRepository.findByRoadAddressLike("%" + curAddress + "%");
            return barsByAddress.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    // 평균 score가 4점 이상인 barId 반환
    public List<BarEntity> getAllBarByScore() {
        List<Integer> barIds = reviewRepository.findBarAByAvgScore();
        return barRepository.findAllByBarIdIn(barIds);
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
