package com.example.ptuxiakh.repository;

import com.example.ptuxiakh.model.PlacePackage.Place;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends MongoRepository<Place, Long> {

    //List<Place> findAllByTypes(String type);

    @Query("{ 'types' : { $all: ?0 } }")
    List<Place> findAllByTypes(List<String> types);
}
