package com.Ohsul.Ohsul.domain;

import com.Ohsul.Ohsul.entity.Bar;
import com.Ohsul.Ohsul.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(FavoriteId.class) // 복합키
@Table(name = "favorite")
public class Favorite {
    @Id
    @ManyToOne
    @JoinColumn(name = "barId")
    private Bar bar;

    @Id
    @ManyToOne
    @JoinColumn(name = "userNumber")
    private User user;
}

