package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.BarReviewDTO;
import com.Ohsul.Ohsul.dto.UserReviewDTO;
import com.Ohsul.Ohsul.entity.ReviewEntity;
import com.Ohsul.Ohsul.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public List<BarReviewDTO> getBarReview(Integer reviewId) {
        List<ReviewEntity> result = reviewRepository.findByReviewId(reviewId);
        List<BarReviewDTO> reviews = new ArrayList<>();

        for (ReviewEntity i : result) {
            BarReviewDTO barReviewDTO = BarReviewDTO.builder()
                    .content(i.getContent())
                    .score(i.getScore())
                    .reviewImg(i.getReviewImg()).build();

            reviews.add(barReviewDTO);
        }
        return reviews;
    }
}
