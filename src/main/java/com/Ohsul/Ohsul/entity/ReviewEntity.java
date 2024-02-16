package com.Ohsul.Ohsul.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "score", nullable = false, length = 1)
    private Integer score;

    @Column(name = "reviewImg")
    private String reviewImg;

    @Column(name = "nickname", length = 20)
    private String nickname;

    @Column(name = "reviewPw", length = 20)
    private String reviewPw;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barId")
    private BarEntity bar;

    @ManyToOne
    @JoinColumn(name = "userNumber")
    private UserEntity user;

}
