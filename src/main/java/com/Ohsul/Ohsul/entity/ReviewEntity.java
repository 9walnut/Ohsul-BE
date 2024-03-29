package com.Ohsul.Ohsul.entity;

import com.Ohsul.Ohsul.dto.BarReviewDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@DynamicUpdate
@Getter
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
    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barId")
    private BarEntity bar;

    @ManyToOne
    @JoinColumn(name = "userNumber")
    private UserEntity user;

    public void updateReview(BarReviewDTO barReviewDTO, String reviewImgUrl) {
        this.content = barReviewDTO.getContent();
        this.score = barReviewDTO.getScore();
        this.reviewImg = reviewImgUrl;
        this.nickname = barReviewDTO.getNickname();
    }

}
