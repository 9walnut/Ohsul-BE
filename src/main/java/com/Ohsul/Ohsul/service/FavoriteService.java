package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.FavoriteDTO;
import com.Ohsul.Ohsul.dto.FavoriteRegisterDTO;
import com.Ohsul.Ohsul.entity.BarEntity;
import com.Ohsul.Ohsul.entity.FavoriteEntity;
import com.Ohsul.Ohsul.entity.FavoriteKey;
import com.Ohsul.Ohsul.entity.UserEntity;
import com.Ohsul.Ohsul.repository.BarRepository;
import com.Ohsul.Ohsul.repository.FavoriteRepository;
import com.Ohsul.Ohsul.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BarRepository barRepository;
    @Autowired
    FavoriteRepository favoriteRepository;
    public void addFavorite(FavoriteRegisterDTO favoriteRegisterDTO) {
        UserEntity user = userRepository.findByUserNumber(favoriteRegisterDTO.getUserNumber())
                .orElseThrow(() -> new RuntimeException("사용자 정보 없음"));

        BarEntity bar = barRepository.findById(favoriteRegisterDTO.getBarId())
                .orElseThrow(() -> new RuntimeException("가게 정보 없음"));

        FavoriteEntity favorite = FavoriteEntity.builder()
                .user(user).bar(bar).build();

        favoriteRepository.save(favorite);
    }

    public void deleteFavorite(FavoriteRegisterDTO favoriteRegisterDTO) {
        UserEntity user = userRepository.findByUserNumber(favoriteRegisterDTO.getUserNumber())
                .orElseThrow(() -> new RuntimeException("사용자 정보 없음"));

        BarEntity bar = barRepository.findById(favoriteRegisterDTO.getBarId())
                .orElseThrow(() -> new RuntimeException("가게 정보 없음"));

        FavoriteKey key = FavoriteKey.builder()
                .user(favoriteRegisterDTO.getUserNumber()).bar(favoriteRegisterDTO.getBarId()).build();

        favoriteRepository.deleteById(key);
    }
}
