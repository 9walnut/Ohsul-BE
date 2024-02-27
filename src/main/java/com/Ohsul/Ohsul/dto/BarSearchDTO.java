package com.Ohsul.Ohsul.dto;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class BarSearchDTO {
  private List<String> telephone = new ArrayList<>();
  private List<String> barName = new ArrayList<>();
  private List<String> roadAddress = new ArrayList<>();
}

