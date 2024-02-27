package com.Ohsul.Ohsul.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ReviewDTO {
  private Integer barId;
  private Integer reviewId;
  private String content;
  private String reviewImg;
  private Date date;

  private List<Integer> alcoholTags;
  private List<Integer> musicTags;
  private List<Integer> moodTags;

}
