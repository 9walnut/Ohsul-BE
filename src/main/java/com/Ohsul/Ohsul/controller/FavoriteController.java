package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.dto.FavoriteDTO;
import com.Ohsul.Ohsul.dto.FavoriteRegisterDTO;
import com.Ohsul.Ohsul.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/favorite")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @PostMapping("/add")
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteRegisterDTO favoriteRegisterDTO) {
        try {
            favoriteService.addFavorite(favoriteRegisterDTO);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFavorite(@RequestBody FavoriteRegisterDTO favoriteRegisterDTO) {
        try {
            favoriteService.deleteFavorite(favoriteRegisterDTO);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
