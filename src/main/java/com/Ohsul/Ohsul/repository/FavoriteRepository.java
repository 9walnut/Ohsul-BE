package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoriteKey> {
  List<FavoriteEntity> findByUser_UserId(String userId);
}
