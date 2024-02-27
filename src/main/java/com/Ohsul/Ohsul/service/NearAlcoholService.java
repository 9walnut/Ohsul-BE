package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.entity.*;
import com.Ohsul.Ohsul.repository.*;
import com.fasterxml.jackson.databind.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.*;

@Service
public class NearAlcoholService {

  private final BarRepository barRepository;

  public NearAlcoholService(BarRepository barRepository) {
    this.barRepository = barRepository;
  }

  public List<BarListDTO> findBarsByRequestList(List<BarSearchDTO> requests) {
    Set<BarEntity> barSet = new HashSet<>();
    for (BarSearchDTO request : requests) {
      if (!request.getTelephone().isEmpty() || !request.getBarName().isEmpty() || !request.getRoadAddress().isEmpty()) {
        BarEntity bar = new BarEntity();
        if (!request.getTelephone().isEmpty()) {
          bar.setTelephone(request.getTelephone().get(0));
        }
        if (!request.getBarName().isEmpty()) {
          bar.setBarName(request.getBarName().get(0));
        }
        if (!request.getRoadAddress().isEmpty()) {
          bar.setRoadAddress(request.getRoadAddress().get(0));
        }

        // DB에 저장
        barRepository.save(bar);
        barSet.add(bar);
      } else {
        if (!request.getTelephone().isEmpty()) {
          barSet.addAll(barRepository.findAllByTelephoneIn(request.getTelephone()));
        }
        if (!request.getBarName().isEmpty()) {
          barSet.addAll(barRepository.findByBarNameIn(request.getBarName()));
        }
        for (String address : request.getRoadAddress()) {
          barSet.addAll(barRepository.findAllByRoadAddressIn(Collections.singletonList(address)));
        }
      }
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

    return barListDTO;
  }
}
