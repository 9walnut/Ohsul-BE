package com.Ohsul.Ohsul.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userNumber;

    @Column(name = "userId", nullable = false, length = 20)
    private String userId;

    @Column(name = "userPw", nullable = false)
    private String userPw;
    @Column(name = "userName", nullable = false, length = 20)
    private String userName;
    @Column(name = "userNickname", nullable = false, length = 50)
    private String userNickname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ReviewEntity> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoriteEntity> favorites = new ArrayList<>();

    public void changeUserName(String userName) {
        this.userName = userName;
    }

    public void changeUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public void changeUserPw(String userPw) {
        this.userPw = userPw;
    }
}
