package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.dto.FavoriteRegisterDTO;
import com.Ohsul.Ohsul.service.FavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/api/favorite")
@Slf4j
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @PostMapping("/add")
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteRegisterDTO favoriteRegisterDTO, @AuthenticationPrincipal String userId) {
        try {
            favoriteService.addFavorite(favoriteRegisterDTO, userId);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFavorite(@RequestBody FavoriteRegisterDTO favoriteRegisterDTO, @AuthenticationPrincipal String userId) {
        try {
            favoriteService.deleteFavorite(favoriteRegisterDTO, userId);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/favoriteList")
    public ResponseEntity<List<?>> userFavoriteList(@AuthenticationPrincipal String userId) {
        try {
            return ResponseEntity.ok().body(favoriteService.getUserFavoriteList(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonList(e.getMessage()));
        }
    }
}
