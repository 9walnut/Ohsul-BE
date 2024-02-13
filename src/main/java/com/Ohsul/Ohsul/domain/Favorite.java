package com.Ohsul.Ohsul.domain;

import com.Ohsul.Ohsul.entity.BarEntity;
import com.Ohsul.Ohsul.entity.UserEntity;
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
    private BarEntity barEntity;

    @Id
    @ManyToOne
    @JoinColumn(name = "userNumber")
    private UserEntity userEntity;
}

