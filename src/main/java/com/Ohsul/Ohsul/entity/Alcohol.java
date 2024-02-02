package com.Ohsul.Ohsul.entity;

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
  private Bar bar;
}
