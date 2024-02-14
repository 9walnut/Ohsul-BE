package com.Ohsul.Ohsul.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mood")
public class MoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moodId;

    @Column(name = "moodType", nullable = false, length = 50)
    private String moodType;

    @OneToMany(mappedBy = "mood", cascade = CascadeType.ALL)
    private List<BarMoodEntity> barMoods = new ArrayList<>();
}
