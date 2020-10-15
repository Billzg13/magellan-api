package com.example.ptuxiakh.model.SolidSearch;

import com.example.ptuxiakh.model.auth.User;
import org.springframework.data.annotation.Id;

public abstract class SearchRequest {

    @Id
    int id;

    User user;

    public SearchRequest(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public SearchRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
