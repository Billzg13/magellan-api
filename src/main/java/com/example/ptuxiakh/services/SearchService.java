package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.SolidSearch.QuickSearchResponse;

public interface SearchService {
    QuickSearchResponse quickSearch(String userId) throws Exception;

    Boolean saveSearch(String userId, QuickSearchResponse quickSearchResponse);

    Object advancedSearch(String userId, com.example.ptuxiakh.model.SolidSearch.AdvancedSearchRequest advancedSearch) throws Exception;
}
