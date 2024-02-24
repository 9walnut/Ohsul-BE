package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.BarDTO;
import com.Ohsul.Ohsul.dto.BarReviewDTO;
import com.Ohsul.Ohsul.entity.*;
import com.Ohsul.Ohsul.repository.BarAlcoholRepository;
import com.Ohsul.Ohsul.repository.BarMoodRepository;
import com.Ohsul.Ohsul.repository.BarMusicRepository;
import com.Ohsul.Ohsul.repository.BarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BarInfoService {

    @Autowired
    BarRepository barRepository;

    @Autowired
    BarAlcoholRepository barAlcoholRepository;

    @Autowired
    BarMusicRepository barMusicRepository;

    @Autowired
    BarMoodRepository barMoodRepository;

    public BarDTO getBarInfo(String telephone) {
        BarEntity barInfo = barRepository.findByTelephone(telephone);

        List<BarAlcoholEntity> barAlcoholEntityList = barAlcoholRepository.findAllByBar_BarId(barInfo.getBarId());
        List<BarMusicEntity> barMusicEntityList = barMusicRepository.findAllByBar_BarId(barInfo.getBarId());
        List<BarMoodEntity> barMoodEntityList = barMoodRepository.findAllByBar_BarId(barInfo.getBarId());

        return BarDTO.builder()
                .barId(barInfo.getBarId())
                .barName(barInfo.getBarName())
                .barImg(barInfo.getBarImg())
                .telephone(barInfo.getTelephone())
                .description(barInfo.getDescription())
                .snack(barInfo.getSnack())
                .toilet(barInfo.getToilet())
                .parkingArea(barInfo.getParkingArea())
                .alcoholTags(barAlcoholEntityList.stream().map(BarAlcoholEntity::getAlcohol)
                        .map(AlcoholEntity::getAlcoholId).collect(Collectors.toList()))
                .musicTags(barMusicEntityList.stream().map(BarMusicEntity::getMusic)
                        .map(MusicEntity::getMusicId).collect(Collectors.toList()))
                .moodTags(barMoodEntityList.stream().map(BarMoodEntity::getMood)
                        .map(MoodEntity::getMoodId).collect(Collectors.toList()))
                .build();
    }
}
