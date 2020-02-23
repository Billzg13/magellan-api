package com.example.ptuxiakh.model;

import org.springframework.data.annotation.Id;

public class QuickSearch {

    @Id
    String id;

    Object search;

    public QuickSearch() {
    }

    public QuickSearch(String id, Object search) {
        this.id = id;
        this.search = search;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getSearch() {
        return search;
    }

    public void setSearch(Object search) {
        this.search = search;
    }
}
