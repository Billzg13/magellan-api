package com.example.ptuxiakh.model;

import com.example.ptuxiakh.model.auth.User;
import org.springframework.data.annotation.Id;

public class SearchRequest {

    @Id
    String id;

    User user;

    TypeOfSearch typeOfSearch;

    public SearchRequest() {
    }

    public SearchRequest(User user, TypeOfSearch typeOfSearch) {
        this.user = user;
        this.typeOfSearch = typeOfSearch;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TypeOfSearch getTypeOfSearch() {
        return typeOfSearch;
    }

    public void setTypeOfSearch(TypeOfSearch typeOfSearch) {
        this.typeOfSearch = typeOfSearch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
