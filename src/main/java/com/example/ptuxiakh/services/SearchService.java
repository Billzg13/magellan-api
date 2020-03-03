package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.AdvancedSearch;

public interface SearchService {
    Object quickSearh(String userId);
    Object advancedSearch(String userId, AdvancedSearch advancedSearch);
}
