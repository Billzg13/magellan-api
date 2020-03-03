package com.example.ptuxiakh.repository;

import com.example.ptuxiakh.model.PlacePackage.Place;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {
    @Override
    @Query(value="{ }", fields="{ 'name' : 1, 'rating' : 1}")
    List<Place> findAll();

    List<Place> findAllByTypes(String type);
}
