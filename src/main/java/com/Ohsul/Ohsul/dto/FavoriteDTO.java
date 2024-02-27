package com.Ohsul.Ohsul.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class FavoriteDTO {
  private Integer barId;
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
