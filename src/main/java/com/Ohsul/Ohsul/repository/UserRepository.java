package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.domain.*;
import com.Ohsul.Ohsul.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
  boolean existsByUserId(String userId);
  boolean existsByNickname(String nickname);
  UserEntity findByUserId(String userId);

  Optional<UserEntity> findByUserNumber(Integer userNumber);

}
