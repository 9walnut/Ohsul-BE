package com.Ohsul.Ohsul.dto;

import com.Ohsul.Ohsul.entity.BarEntity;
import com.Ohsul.Ohsul.entity.BarAlcoholEntity;
import com.Ohsul.Ohsul.entity.BarMusicEntity;
import com.Ohsul.Ohsul.entity.BarMoodEntity;
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
  private Double AvgScore;

  private List<Integer> alcoholTags;
  private List<Integer> musicTags;
  private List<Integer> moodTags;

  public void setBar(BarEntity bar) {
    this.barId = bar.getBarId();
    this.barName = bar.getBarName();
    this.barImg = bar.getBarImg();
    this.telephone = bar.getTelephone();
    this.description = bar.getDescription();
    this.snack = bar.getSnack();
    this.toilet = bar.getToilet();
    this.parkingArea = bar.getParkingArea();

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
