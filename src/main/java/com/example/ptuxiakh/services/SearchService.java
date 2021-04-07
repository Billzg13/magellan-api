package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.SolidSearch.QuickSearchHistoryV2;
import com.example.ptuxiakh.model.SolidSearch.QuickSearchResponse;

import java.util.List;

public interface SearchService {
    QuickSearchResponse quickSearch(String userId) throws Exception;

    Boolean saveSearch(String userId, QuickSearchResponse quickSearchResponse);

    List<QuickSearchHistoryV2> getLatestQuickSearches(String userId);

    Object advancedSearch(String userId, com.example.ptuxiakh.model.SolidSearch.AdvancedSearchRequest advancedSearch) throws Exception;
}
