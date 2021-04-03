package com.example.ptuxiakh.repository;

import com.example.ptuxiakh.model.SolidSearch.QuickSearchHistoryV2;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface QuickSearchHistoryRepositoryV2 extends MongoRepository<QuickSearchHistoryV2, String> {
    ArrayList<QuickSearchHistoryV2> findAllByUserId(String userId);
}
