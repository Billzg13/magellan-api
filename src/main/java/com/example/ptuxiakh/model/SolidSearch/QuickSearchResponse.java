package com.example.ptuxiakh.model.SolidSearch;

import com.example.ptuxiakh.model.Favourite;

import java.util.ArrayList;

public class QuickSearchResponse {
    ArrayList<Favourite> result = new ArrayList<>();

    public QuickSearchResponse(ArrayList<Favourite> result) {
        this.result = result;
    }

    public QuickSearchResponse() {
    }

    public ArrayList<Favourite> getResult() {
        return result;
    }

    public void setResult(ArrayList<Favourite> result) {
        this.result = result;
    }


}
