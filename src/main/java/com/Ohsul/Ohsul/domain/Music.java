package com.Ohsul.Ohsul.domain;

import com.Ohsul.Ohsul.entity.Bar;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "music")
public class Music {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer musicId;
  private String musicType;

  @ManyToOne
  @JoinColumn(name = "barId")
  private Bar bar;
}
