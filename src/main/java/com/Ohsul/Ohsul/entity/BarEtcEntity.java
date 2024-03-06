package com.Ohsul.Ohsul.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "barEtc")
public class BarEtcEntity {

    @Id
    private Integer barId;

    @Column(name = "toilet", columnDefinition = "TINYINT(1)")
    private Boolean toilet;

    @Column(name = "parkingArea", columnDefinition = "TINYINT(1)")
    private Boolean parkingArea;

    @Column(name = "snack", columnDefinition = "TINYINT(1)")
    private Boolean snack;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "barId")
    private BarEntity bar;

//    @OneToOne
//    @JoinColumn(name = "reviewId", referencedColumnName = "reviewId")
//    private ReviewEntity review;
}
