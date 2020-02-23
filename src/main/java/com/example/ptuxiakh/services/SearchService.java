package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.AdvancedSearch;
import com.example.ptuxiakh.model.QuickSearch;

public interface SearchService {
    public abstract Object quickSearh(String userId, QuickSearch quickSearch);
    public abstract  Object advancedSearch(String userId, AdvancedSearch advancedSearch);
}
