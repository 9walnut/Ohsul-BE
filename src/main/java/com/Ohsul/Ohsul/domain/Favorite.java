package com.Ohsul.Ohsul.domain;

import com.Ohsul.Ohsul.entity.BarEntity;
import com.Ohsul.Ohsul.entity.UserEntity;
import lombok.Getter;

@Getter
public class Favorite {
    private BarEntity barEntity;
    private UserEntity userEntity;
}

