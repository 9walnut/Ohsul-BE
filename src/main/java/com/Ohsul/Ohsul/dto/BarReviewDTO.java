package com.Ohsul.Ohsul.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

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
    private Date date;

    private List<Integer> alcoholTags;
    private List<Integer> musicTags;
    private List<Integer> moodTags;
}
