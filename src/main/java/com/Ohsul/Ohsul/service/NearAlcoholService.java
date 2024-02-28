package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.entity.*;
import com.Ohsul.Ohsul.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class NearAlcoholService {

  @Autowired
  BarRepository barRepository;
  @Autowired
  ReviewRepository reviewRepository;

  @Autowired
  BarEntityToDTOConverter barEntityToDTOConverter;

  public NearAlcoholService(BarRepository barRepository, ReviewRepository reviewRepository) {

    this.barRepository = barRepository;
    this.reviewRepository = reviewRepository;
  }

  public List<BarListDTO> findBarsByRequestList(List<BarSearchDTO> requests) {
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
    return barEntityToDTOConverter.convertEntityToDto(barentity);

  }
}
