package com.example.ptuxiakh.repository;

import com.example.ptuxiakh.model.QuickSearch;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuickSearchRepository extends MongoRepository<QuickSearch, String> {
}
