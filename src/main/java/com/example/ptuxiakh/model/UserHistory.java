package com.example.ptuxiakh.model;

import org.springframework.data.annotation.Id;

public class UserHistory {

    @Id
    private String id;

    private String userId;

    private TypeOfSearch typeOfSearch;

    private AdvancedSearchRequest advancedSearchRequest;

    public UserHistory() {
    }

    public UserHistory(String id, String userId, TypeOfSearch typeOfSearch, AdvancedSearchRequest advancedSearchRequest) {
        this.id = id;
        this.userId = userId;
        this.typeOfSearch = typeOfSearch;
        this.advancedSearchRequest = advancedSearchRequest;
    }

    public UserHistory(String userId, TypeOfSearch typeOfSearch, AdvancedSearchRequest advancedSearchRequest) {
        this.userId = userId;
        this.typeOfSearch = typeOfSearch;
        this.advancedSearchRequest = advancedSearchRequest;
    }

    public UserHistory(String userId, TypeOfSearch typeOfSearch) {
        this.userId = userId;
        this.typeOfSearch = typeOfSearch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public TypeOfSearch getTypeOfSearch() {
        return typeOfSearch;
    }

    public void setTypeOfSearch(TypeOfSearch typeOfSearch) {
        this.typeOfSearch = typeOfSearch;
    }

    public AdvancedSearchRequest getAdvancedSearchRequest() {
        return advancedSearchRequest;
    }

    public void setAdvancedSearchRequest(AdvancedSearchRequest advancedSearchRequest) {
        this.advancedSearchRequest = advancedSearchRequest;
    }
}
