package com.Ohsul.Ohsul.entity;

import com.Ohsul.Ohsul.domain.Favorite;
import com.Ohsul.Ohsul.domain.Review;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userNumber;

    @Column(name = "userId", nullable = false, length = 20)
    private String userId;

    @Column(name = "userPw", nullable = false)
    private String userPw;
    @Column(name = "salt", nullable = false)
    private String salt;
    @Column(name = "userName", nullable = false, length = 20)
    private String userName;
    @Column(name = "userNickname", nullable = false, length = 50)
    private String userNickname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Favorite> favorites = new ArrayList<>();
}
