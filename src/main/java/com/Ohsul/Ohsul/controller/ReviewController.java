package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.dto.BarReviewDTO;
import com.Ohsul.Ohsul.dto.UserReviewDTO;
import com.Ohsul.Ohsul.service.ReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ohsul")
public class ReviewController {

//    @GetMapping("/barId/review")
//    public List<BarReviewDTO> result = ReviewService.getBarReview(Integer barId);
//    return result;
//    }
}
