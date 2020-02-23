package com.example.ptuxiakh.model;

import org.springframework.data.annotation.Id;

public class AdvancedSearch {

    @Id
    String id;

    Object search;

    public AdvancedSearch() {
    }

    public AdvancedSearch(String id, Object search) {
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
