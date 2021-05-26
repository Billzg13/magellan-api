package com.example.ptuxiakh.model.SolidSearch;

import com.example.ptuxiakh.model.SearchResult;

import java.util.ArrayList;

public class QuickSearchResponse {
    ArrayList<SearchResult> result = new ArrayList<>();

    public QuickSearchResponse(ArrayList<SearchResult> result) {
        this.result = result;
    }

    public QuickSearchResponse() {
    }

    public ArrayList<SearchResult> getResult() {
        return result;
    }

    public void setResult(ArrayList<SearchResult> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "QuickSearchResponse{" +
                "result=" + result +
                '}';
    }
}
