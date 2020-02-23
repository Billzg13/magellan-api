package com.example.ptuxiakh.repository;


import com.example.ptuxiakh.model.AdvancedSearch;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdvancedSearchRepository extends MongoRepository<AdvancedSearch, String> {
}
