package com.Ohsul.Ohsul.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alcohol")
public class AlcoholEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer alcoholId;

    @Column(name = "alcoholType", nullable = false, length = 50)
    private String alcoholType;

    @OneToMany(mappedBy = "alcohol", cascade = CascadeType.ALL)
    private List<BarAlcoholEntity> barAlcohols = new ArrayList<>();
}
