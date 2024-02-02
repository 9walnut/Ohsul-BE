package com.Ohsul.Ohsul.entity;

import com.Ohsul.Ohsul.domain.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "bar")
public class Bar {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer barId;
  private String barName;
  private String roadAddress;
  private String localAddress;
  private String description;
  private String telephone;
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
}
