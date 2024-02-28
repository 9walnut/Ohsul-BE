package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.entity.*;
import com.Ohsul.Ohsul.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class NearAlcoholService {

  @Autowired
  BarRepository barRepository;
  @Autowired
  ReviewRepository reviewRepository;

  @Autowired
  BarEntityToDTOConverter barEntityToDTOConverter;
  @Autowired
  FindBarByRequestList findBarByRequestList;

  public NearAlcoholService(BarRepository barRepository, ReviewRepository reviewRepository) {

    this.barRepository = barRepository;
    this.reviewRepository = reviewRepository;
  }

  public List<BarListDTO> findBarsByRequestList(List<BarSearchDTO> requests) {
    return findBarByRequestList.findBarsByRequestList(requests);
  }
}
