package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.BarListDTO;
import com.Ohsul.Ohsul.dto.BarSearchDTO;
import com.Ohsul.Ohsul.entity.BarEntity;
import com.Ohsul.Ohsul.repository.BarRepository;
import com.Ohsul.Ohsul.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FindBarByRequestList {

    @Autowired
    BarEntityToDTOConverter barEntityToDTOConverter;
    @Autowired
    BarRepository barRepository;
    @Autowired
    ReviewRepository reviewRepository;

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
                bar.setBarImg("https://ohsul.s3.ap-northeast-2.amazonaws.com/reviewImg/noimage.png");
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
