package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.AdvancedSearchRequest;
import com.example.ptuxiakh.model.TypeOfSearch;
import com.example.ptuxiakh.model.UserHistory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SearchService {
    ResponseEntity quickSearh(String userId);
    Object quickSearch2(String userId) throws Exception;
    Object advancedSearch(String userId, AdvancedSearchRequest advancedSearchRequest);
    void saveSearch(String userId, TypeOfSearch typeOfSearch, AdvancedSearchRequest advancedSearchRequest);
    List<UserHistory> getUserHistory(String userId);
    Object advancedSearch2(String userId, com.example.ptuxiakh.model.SolidSearch.AdvancedSearchRequest advancedSearch);
}
