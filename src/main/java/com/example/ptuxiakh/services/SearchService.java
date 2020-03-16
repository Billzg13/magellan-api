package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.AdvancedSearchRequest;
import org.springframework.http.ResponseEntity;

public interface SearchService {
    ResponseEntity quickSearh(String userId);
    Object advancedSearch(String userId, AdvancedSearchRequest advancedSearchRequest);
}
