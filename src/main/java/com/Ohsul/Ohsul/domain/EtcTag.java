package com.Ohsul.Ohsul.domain;

import com.Ohsul.Ohsul.entity.Bar;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "etcTag")
public class EtcTag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer etcTagId;
  private String toilet;
  private String parkingArea;
  private String snackGood;

  @ManyToOne
  @JoinColumn(name = "barId")
  private Bar bar;
}
