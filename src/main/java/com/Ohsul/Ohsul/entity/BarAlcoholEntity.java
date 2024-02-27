package com.Ohsul.Ohsul.entity;

import com.Ohsul.Ohsul.domain.Alcohol;
import com.Ohsul.Ohsul.domain.Bar;
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
@Table(name = "barAlcohol")
@IdClass(BarAlcoholKey.class)
public class BarAlcoholEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barId")
    private BarEntity bar;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alcoholId")
    private AlcoholEntity alcohol;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reviewId", referencedColumnName = "reviewId")
    private ReviewEntity review;

    public Integer getAlcoholId() {
        return alcohol.getAlcoholId();
    }
}
