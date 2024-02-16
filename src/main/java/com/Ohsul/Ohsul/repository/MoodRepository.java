package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.entity.MoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoodRepository extends JpaRepository<MoodEntity, Integer> {
}
