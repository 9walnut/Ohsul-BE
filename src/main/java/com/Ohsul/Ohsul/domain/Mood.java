package com.Ohsul.Ohsul.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mood")
public class Mood {
  @Id
  @Column(name="moodId")
  private int moodId;

  @ManyToOne
  @JoinColumn(name="barId")
  private Bar bar;
}