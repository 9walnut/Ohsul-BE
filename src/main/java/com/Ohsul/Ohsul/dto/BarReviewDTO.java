package com.Ohsul.Ohsul.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BarReviewDTO {
    private String content;
    private Integer score;
    private String reviewImg;
    private String nickname;
    private String reviewPw;

    private List<Integer> alcoholTags;
    private List<Integer> musicTags;
    private List<Integer> moodTags;
}
