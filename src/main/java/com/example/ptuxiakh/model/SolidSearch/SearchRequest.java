package com.example.ptuxiakh.model.SolidSearch;

import com.example.ptuxiakh.model.auth.User;
import org.springframework.data.annotation.Id;

public abstract class SearchRequest {

    @Id
    int id;

    User user;

    String type;

    public SearchRequest(int id, User user, String type) {
        this.id = id;
        this.user = user;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
