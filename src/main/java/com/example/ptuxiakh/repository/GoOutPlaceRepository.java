package com.example.ptuxiakh.repository;

import com.example.ptuxiakh.model.general.GoOutPlace;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoOutPlaceRepository extends MongoRepository<GoOutPlace, String> {
}
