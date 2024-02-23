package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.BarListDTO;
import com.Ohsul.Ohsul.entity.BarEntity;
import com.Ohsul.Ohsul.repository.BarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodayAlcoholService {

    @Autowired
    BarRepository barRepository;

    // 넘어온 주소 정보(구 단위)를 포함하는 술집 정보 모두 반환
    public List<BarListDTO> searchBarByAddress(String sulAddress) {
        List<BarEntity> barsByAddress = barRepository.findByRoadAddressLike("%" + sulAddress + "%");
        return barsByAddress.stream().map(this::convertEntityToDto).collect(Collectors.toList());
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
