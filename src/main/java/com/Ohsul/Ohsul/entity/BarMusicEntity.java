package com.Ohsul.Ohsul.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(BarMusicKey.class)
@Table(name = "barMusic")
public class BarMusicEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barId")
    private BarEntity bar;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "musicId")
    private MusicEntity music;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reviewId", referencedColumnName = "reviewId")
    private ReviewEntity review;

}
