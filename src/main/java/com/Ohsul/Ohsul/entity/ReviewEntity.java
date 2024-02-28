package com.Ohsul.Ohsul.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

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

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barId")
    private BarEntity bar;

    @ManyToOne
    @JoinColumn(name = "userNumber")
    private UserEntity user;

}
