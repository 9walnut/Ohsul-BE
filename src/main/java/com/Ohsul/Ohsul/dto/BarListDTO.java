package com.Ohsul.Ohsul.dto;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class BarListDTO {
  private String barName;
  private String barImg;
  private List<String> telephone;

  private List<Integer> alcoholTags;
  private List<Integer> musicTags;
  private List<Integer> moodTags;
}
