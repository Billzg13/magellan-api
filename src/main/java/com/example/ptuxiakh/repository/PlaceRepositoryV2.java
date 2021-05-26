package com.example.ptuxiakh.repository;

import com.example.ptuxiakh.model.PlacePackage.Place;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PlaceRepositoryV2 extends PagingAndSortingRepository<Place, Long> {

    @Query("{ 'types' : { $all: ?0 } }")
    List<Place> findAllByTypes(List<String> types, Pageable pageable);

    Place findByName(String name);
}
