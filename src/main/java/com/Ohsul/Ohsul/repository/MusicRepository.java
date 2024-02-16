package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.entity.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<MusicEntity, Integer> {
}
