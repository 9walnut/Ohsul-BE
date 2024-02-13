package com.Ohsul.Ohsul.domain;

import com.Ohsul.Ohsul.entity.BarEntity;
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

  @ManyToOne
  @JoinColumn(name = "barId")
  private BarEntity barEntity;
}
