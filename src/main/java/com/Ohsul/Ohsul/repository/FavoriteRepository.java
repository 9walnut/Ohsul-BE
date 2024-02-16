package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.entity.*;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Integer> {
  List<FavoriteEntity> findByUser(UserEntity user);
}
