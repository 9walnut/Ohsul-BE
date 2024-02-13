package com.Ohsul.Ohsul.domain;

import com.Ohsul.Ohsul.entity.BarEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name="alcohol")
public class Alcohol {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer alcoholId;
  private String alcoholType;

  @ManyToOne
  @JoinColumn(name = "barId")
  private BarEntity barEntity;
}
