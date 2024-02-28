package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.BarListDTO;
import com.Ohsul.Ohsul.entity.BarEntity;
import com.Ohsul.Ohsul.repository.BarRepository;
import com.Ohsul.Ohsul.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    BarEntityToDTOConverter barEntityToDTOConverter;

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
        return barEntityToDTOConverter.convertEntityToDto(barentity);
    }
}
