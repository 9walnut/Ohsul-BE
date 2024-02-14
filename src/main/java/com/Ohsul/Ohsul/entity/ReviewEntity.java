package com.Ohsul.Ohsul.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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
    private Float score;

    @Column(name = "reviewImg")
    private String reviewImg;

    @Column(name = "nickname", length = 20)
    private String nickname;

    @Column(name = "reviewPw", length = 20)
    private String reviewPw;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barId")
    private BarEntity bar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userNumber")
    private UserEntity user;
}
