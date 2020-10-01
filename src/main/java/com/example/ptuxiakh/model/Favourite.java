package com.example.ptuxiakh.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class Favourite {

    Long placeId;

    String name;

    @Min(0)
    @Max(5)
    int rating;

    public Favourite() {
    }

    public Favourite(Long placeId, String name, int rating) {
        this.placeId = placeId;
        this.name = name;
        this.rating = rating;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
