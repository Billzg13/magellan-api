package com.example.ptuxiakh.services;

public interface SearchService {
    Object quickSearch(String userId) throws Exception;

    Object advancedSearch(String userId, com.example.ptuxiakh.model.SolidSearch.AdvancedSearchRequest advancedSearch) throws Exception;
}
