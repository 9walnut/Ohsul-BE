package com.Ohsul.Ohsul.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "bar")
public class BarEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer barId;

  @Column(name = "barName", nullable = false, length = 50)
  private String barName;

  @Column(name = "roadAddress", nullable = false)
  private String roadAddress;
  @Column(name = "localAddress")
  private String localAddress;

  @Column(name = "description")
  private String description;

  @Column(name = "telephone", length = 20)
  private String telephone;

  @Column(name = "barImg")
  private String barImg;

  @OneToOne(mappedBy = "bar", cascade = CascadeType.ALL)
  private BarEtcEntity barEtc;

  @OneToMany(mappedBy = "bar", cascade = CascadeType.ALL)
  private List<ReviewEntity> reviews = new ArrayList<>();

  @OneToMany(mappedBy = "bar", cascade = CascadeType.ALL)
  private List<FavoriteEntity> favorites = new ArrayList<>();

  @OneToMany(mappedBy = "bar", cascade = CascadeType.ALL)
  private List<BarAlcoholEntity> barAlcohols = new ArrayList<>();

  @OneToMany(mappedBy = "bar", cascade = CascadeType.ALL)
  private List<BarMusicEntity> barMusics = new ArrayList<>();

  @OneToMany(mappedBy = "bar", cascade = CascadeType.ALL)
  private List<BarMoodEntity> barMoods = new ArrayList<>();
}
