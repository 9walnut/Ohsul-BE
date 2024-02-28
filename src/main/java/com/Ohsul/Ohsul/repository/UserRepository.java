package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
  Boolean existsByUserId(String userId);
  Boolean existsByUserNickname(String userNickname);
  Optional<UserEntity> findByUserId(String userId);

  Optional<UserEntity> findByUserNumber(Integer userNumber);
}
