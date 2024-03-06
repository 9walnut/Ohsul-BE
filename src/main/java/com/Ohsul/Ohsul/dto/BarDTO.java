package com.Ohsul.Ohsul.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class BarDTO {
    private Integer barId;
    private String barName;
    private String barImg;
    private String telephone;

    private String description;

    private Map<String, Boolean> etcTags;

    private List<Integer> alcoholTags;
    private List<Integer> musicTags;
    private List<Integer> moodTags;
}
