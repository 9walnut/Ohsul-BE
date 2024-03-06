package com.Ohsul.Ohsul.dto;

import com.Ohsul.Ohsul.entity.BarEntity;
import com.Ohsul.Ohsul.entity.BarAlcoholEntity;
import com.Ohsul.Ohsul.entity.BarMusicEntity;
import com.Ohsul.Ohsul.entity.BarMoodEntity;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class FavoriteDTO {
  private Integer barId;
  private String barName;
  private String barImg;
  private String telephone;

  private String description;
  private Double AvgScore;

  private Map<String, Boolean> etcTags;

  private List<Integer> alcoholTags;
  private List<Integer> musicTags;
  private List<Integer> moodTags;

  public void setBar(BarEntity bar) {
    this.barId = bar.getBarId();
    this.barName = bar.getBarName();
    this.barImg = bar.getBarImg();
    this.telephone = bar.getTelephone();
    this.description = bar.getDescription();

    Map<String, Boolean> etcTags = new HashMap<>();
    etcTags.put("toilet", bar.getBarEtc().getToilet());
    etcTags.put("parkingArea", bar.getBarEtc().getParkingArea());
    etcTags.put("snack", bar.getBarEtc().getSnack());
    this.etcTags = etcTags;

    this.alcoholTags = bar.getBarAlcohols().stream()
            .map(BarAlcoholEntity::getAlcoholId)
            .toList();

    this.musicTags = bar.getBarMusics().stream()
            .map(BarMusicEntity::getMusicId)
            .toList();

    this.moodTags = bar.getBarMoods().stream()
            .map(BarMoodEntity::getMoodId)
            .toList();
  }
}
