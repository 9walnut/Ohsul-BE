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
@Table(name = "music")
public class MusicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer musicId;

    @Column(name = "musicType", nullable = false, length = 50)
    private String musicType;

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    private List<BarMusicEntity> barMusics = new ArrayList<>();
}
