package com.Ohsul.Ohsul.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BarDTO {
    private String barName;
    private String barImg;
    private String telephone;

    private String description;
    private Boolean snack;
    private Boolean toilet;
    private Boolean parkingArea;

    private List<Integer> alcoholTags;
    private List<Integer> musicTags;
    private List<Integer> moodTags;
}
