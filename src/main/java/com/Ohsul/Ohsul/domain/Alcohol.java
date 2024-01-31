package com.Ohsul.Ohsul.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "alcohol")
public class Alcohol {
  @Id
  @Column(name = "alcoholId")
  private int alcoholId;

  @ManyToOne
  @JoinColumn(name = "barId")
  private Bar bar;

}
