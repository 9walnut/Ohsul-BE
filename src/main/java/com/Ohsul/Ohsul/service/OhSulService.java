package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.*;
import com.Ohsul.Ohsul.entity.*;
import com.Ohsul.Ohsul.repository.BarRepository;
import com.Ohsul.Ohsul.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OhSulService {
    @Autowired
     BarRepository barRepository;
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    FindBarByRequestList findBarByRequestList;

    @Autowired
    BarEntityToDTOConverter barEntityToDTOConverter;

    public OhSulService(BarRepository barRepository, ReviewRepository reviewRepository) {

        this.barRepository = barRepository;
        this.reviewRepository = reviewRepository;
    }


    public List<BarListDTO> getBarScoreAndReviewInfo(List<BarSearchDTO> requests) {
        return findBarByRequestList.findBarsByRequestList(requests);
    }
}
