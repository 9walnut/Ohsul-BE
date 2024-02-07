package com.Ohsul.Ohsul.domain;

import com.Ohsul.Ohsul.entity.Bar;
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

  @Column(name = "moodType")
  private String moodType;

  @ManyToOne
  @JoinColumn(name="barId")
  private Bar bar;
}