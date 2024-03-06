package com.Ohsul.Ohsul.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BarReviewDTO {
    private Integer reviewId;
    private String content;
    private Integer score;
    private String reviewImg;
    private String nickname;
    private String reviewPw;
    private LocalDateTime date;

//    private Map<String, Boolean> etcTags;

    private List<Integer> alcoholTags;
    private List<Integer> musicTags;
    private List<Integer> moodTags;
}
