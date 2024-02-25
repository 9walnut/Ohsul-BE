package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.domain.*;
import com.Ohsul.Ohsul.entity.*;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoriteKey> {
//  List<FavoriteEntity> findByUserId(UserEntity userId);
  List<FavoriteEntity> findByUser_UserId(String userId);
}
