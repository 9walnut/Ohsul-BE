package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.BarDTO;
import com.Ohsul.Ohsul.entity.*;
import com.Ohsul.Ohsul.repository.BarAlcoholRepository;
import com.Ohsul.Ohsul.repository.BarMoodRepository;
import com.Ohsul.Ohsul.repository.BarMusicRepository;
import com.Ohsul.Ohsul.repository.BarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public BarDTO getBarInfo(Integer barId) {
        BarEntity barInfo = barRepository.findById(barId).orElseThrow(() -> new RuntimeException("바 정보 없음"));

        List<BarAlcoholEntity> barAlcoholEntityList = barAlcoholRepository.findAllByBar_BarId(barInfo.getBarId());
        List<BarMusicEntity> barMusicEntityList = barMusicRepository.findAllByBar_BarId(barInfo.getBarId());
        List<BarMoodEntity> barMoodEntityList = barMoodRepository.findAllByBar_BarId(barInfo.getBarId());

        return BarDTO.builder()
                .barId(barInfo.getBarId())
                .barName(barInfo.getBarName())
                .barImg(barInfo.getBarImg())
                .telephone(barInfo.getTelephone())
                .description(barInfo.getDescription())
                .etcTags(new HashMap<String, Boolean>() {{
                    put("toilet", barInfo.getBarEtc().getToilet());
                    put("parkingArea", barInfo.getBarEtc().getParkingArea());
                    put("snack", barInfo.getBarEtc().getSnack());
                }})
                .alcoholTags(barAlcoholEntityList.stream().map(BarAlcoholEntity::getAlcohol)
                        .map(AlcoholEntity::getAlcoholId).collect(Collectors.toList()))
                .musicTags(barMusicEntityList.stream().map(BarMusicEntity::getMusic)
                        .map(MusicEntity::getMusicId).collect(Collectors.toList()))
                .moodTags(barMoodEntityList.stream().map(BarMoodEntity::getMood)
                        .map(MoodEntity::getMoodId).collect(Collectors.toList()))
                .build();
    }
}
