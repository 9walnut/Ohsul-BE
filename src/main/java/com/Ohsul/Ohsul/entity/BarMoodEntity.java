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
@IdClass(BarMoodKey.class)
@Table(name = "barMood")
public class BarMoodEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barId")
    private BarEntity bar;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moodId")
    private MoodEntity mood;

//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "reviewId", referencedColumnName = "reviewId")
    private ReviewEntity review;

    public Integer getMoodId() {
        return mood.getMoodId();
    }
}
