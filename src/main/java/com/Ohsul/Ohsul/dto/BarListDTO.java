package com.Ohsul.Ohsul.dto;

import com.Ohsul.Ohsul.entity.BarEtcEntity;
import lombok.*;

import java.util.*;

@Getter
@Setter
public class BarListDTO {
  private Integer barId;
  private String barName;
  private String barImg;

  private Map<String, Integer> etcTags;

  private List<Integer> alcoholTags;
  private List<Integer> musicTags;
  private List<Integer> moodTags;

  private Double barAvgScore;
  private List<BarRecentReviewDTO> barRecentReviews;
}
