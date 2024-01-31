package com.Ohsul.Ohsul.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "music")
public class Music {
  @Id
  @Column(name = "musicId")
  private int musicId;

  @ManyToOne
  @JoinColumn(name = "barId")
  private Bar bar;
}
