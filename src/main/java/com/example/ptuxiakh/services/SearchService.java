package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.SearchType;
import com.example.ptuxiakh.model.SolidSearch.QuickSearchHistoryV2;
import com.example.ptuxiakh.model.SolidSearch.QuickSearchResponse;

import java.util.List;

public interface SearchService {
    QuickSearchResponse quickSearch(String userId, SearchType type) throws Exception;

    String saveSearch(String userId, QuickSearchResponse quickSearchResponse, String type);

    List<QuickSearchHistoryV2> getLatestQuickSearches(String userId);

    QuickSearchHistoryV2 findQuickSearchById(String searchId);

    String advancedSearch(String userId, com.example.ptuxiakh.model.SolidSearch.AdvancedSearchRequest advancedSearch, String searchId) throws Exception;
}
