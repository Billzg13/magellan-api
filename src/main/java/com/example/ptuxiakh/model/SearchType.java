package com.example.ptuxiakh.model;

public class SearchType {

    String type;

    public SearchType(String type) {
        this.type = type;
    }

    public SearchType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SearchType{" +
                "type='" + type + '\'' +
                '}';
    }
}
