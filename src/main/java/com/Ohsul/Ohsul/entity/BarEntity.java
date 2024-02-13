package com.Ohsul.Ohsul.entity;

import com.Ohsul.Ohsul.domain.*;
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

  @Column(name = "barName", nullable = false, length = 20)
  private String barName;

  @Column(name = "roadAddress", nullable = false)
  private String roadAddress;
  @Column(name = "localAddress", nullable = false)
  private String localAddress;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "telephone", nullable = false, length = 11)
  private String telephone;

  @Column(name = "barImg")
  private String barImg;

  @OneToMany(mappedBy = "bar", cascade = CascadeType.ALL)
  private List<Mood> moods = new ArrayList<>();

  @OneToMany(mappedBy = "bar", cascade = CascadeType.ALL)
  private List<Alcohol> alcohols = new ArrayList<>();

  @OneToMany(mappedBy = "bar", cascade = CascadeType.ALL)
  private List<Music> musics = new ArrayList<>();

  @OneToMany(mappedBy = "bar", cascade = CascadeType.ALL)
  private List<Review> reviews = new ArrayList<>();

  @OneToMany(mappedBy = "bar", cascade = CascadeType.ALL)
  private List<EtcTag> etcTags = new ArrayList<>();

  @OneToMany(mappedBy = "bar", cascade = CascadeType.ALL)
  private List<Snack> snacks = new ArrayList<>();

  @OneToMany(mappedBy = "bar", cascade = CascadeType.ALL)
  private List<Favorite> favorites = new ArrayList<>();
}
