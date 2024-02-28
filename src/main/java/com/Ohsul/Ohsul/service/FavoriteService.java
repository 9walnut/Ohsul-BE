package com.Ohsul.Ohsul.service;

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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BarRepository barRepository;
    @Autowired
    FavoriteRepository favoriteRepository;
    public void addFavorite(FavoriteRegisterDTO favoriteRegisterDTO, String userId) {
        UserEntity user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("사용자 정보 없음"));

        BarEntity bar = barRepository.findById(favoriteRegisterDTO.getBarId())
                .orElseThrow(() -> new RuntimeException("가게 정보 없음"));

        FavoriteEntity favorite = FavoriteEntity.builder()
                .user(user).bar(bar).build();

        favoriteRepository.save(favorite);
    }

    public void deleteFavorite(FavoriteRegisterDTO favoriteRegisterDTO, String userId) {
        UserEntity user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("사용자 정보 없음"));

        BarEntity bar = barRepository.findById(favoriteRegisterDTO.getBarId())
                .orElseThrow(() -> new RuntimeException("가게 정보 없음"));

        FavoriteKey key = FavoriteKey.builder()
                .user(user.getUserNumber()).bar(favoriteRegisterDTO.getBarId()).build();

        favoriteRepository.deleteById(key);
    }

    public List<Integer> getUserFavoriteList(String userId) {
        return favoriteRepository.findByUser_UserId(userId)
                .stream().map(favoriteEntity -> favoriteEntity.getBar().getBarId())
                .toList();
    }
}
