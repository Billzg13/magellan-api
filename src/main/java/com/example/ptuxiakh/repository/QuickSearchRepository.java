package com.example.ptuxiakh.repository;

import com.example.ptuxiakh.model.SearchRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuickSearchRepository extends MongoRepository<SearchRequest, String> {
}
