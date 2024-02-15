package com.Ohsul.Ohsul.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BarReviewDTO {
    private String content;
    private Integer score;
    private String reviewImg;
    private String nickname;
}
