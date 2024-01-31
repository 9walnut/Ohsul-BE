package com.Ohsul.Ohsul.repository;

import com.Ohsul.Ohsul.domain.Bar;
import org.springframework.data.mongodb.repository.MongoRepository;

// key가 String인 Bar 객체에 대해 CRUD 기능을 만들어줌
public interface BarRepository extends MongoRepository<Bar, String> {

}
