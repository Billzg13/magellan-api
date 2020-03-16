package com.example.ptuxiakh.repository;


import com.example.ptuxiakh.model.AdvancedSearchRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdvancedSearchRepository extends MongoRepository<AdvancedSearchRequest, String> {
}
