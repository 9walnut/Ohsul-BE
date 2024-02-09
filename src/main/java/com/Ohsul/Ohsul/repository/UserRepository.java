package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.*;
import java.util.*;

public interface UserRepository extends JpaRepository<User, Integer> {
  boolean existsByLoginId(String userId);
  boolean existsByNickname(String nickname);
  Optional<User> findByLoginId(String userId);
}
