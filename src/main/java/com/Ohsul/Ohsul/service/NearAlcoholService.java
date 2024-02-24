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

  public List<BarListDTO> findBarsByTelephoneAndName(List<String> telephones, List<String> barNames) {
    List<BarEntity> bars = barRepository.findAllByTelephoneIn(telephones);

    if (bars.isEmpty() && !barNames.isEmpty()) {
      bars = barRepository.findByBarNameIn(barNames);
    }

    return bars.stream().map(this::convertEntityToDto).collect(Collectors.toList());
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
