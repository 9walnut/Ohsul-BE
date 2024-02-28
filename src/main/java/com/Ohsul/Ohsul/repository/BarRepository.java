package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface BarRepository extends JpaRepository<BarEntity, Integer> {
  List<BarEntity> findByRoadAddressLike(String curAddress);
  List<BarEntity> findAllByBarIdIn(List<Integer> ids);
  BarEntity findByTelephone(String telephone);
  BarEntity findByBarName(String barName);
}
