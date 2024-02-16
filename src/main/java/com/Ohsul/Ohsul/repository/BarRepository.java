package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.entity.BarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// key가 Integer인 Bar 객체에 대해 CRUD 기능을 만들어줌
public interface BarRepository extends JpaRepository<BarEntity, Integer> {

}
