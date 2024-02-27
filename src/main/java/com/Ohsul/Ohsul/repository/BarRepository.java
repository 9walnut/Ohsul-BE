package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

// key가 Integer인 Bar 객체에 대해 CRUD 기능을 만들어줌
public interface BarRepository extends JpaRepository<BarEntity, Integer> {
  List<BarEntity> findAllByTelephoneIn(List<String> telephone);
  List<BarEntity> findByBarNameIn(List<String> barName);
  List<BarEntity> findAllByRoadAddressIn(List<String> roadAddress);
  List<BarEntity> findByRoadAddressLike(String curAddress);
  List<BarEntity> findAllByBarIdIn(List<Integer> ids);
  BarEntity findByTelephone(String telephone);
}
