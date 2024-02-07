package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
