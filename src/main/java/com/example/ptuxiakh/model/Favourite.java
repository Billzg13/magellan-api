package com.example.ptuxiakh.model;

public class Favourite {

    String placeId;

    Double rating;

    public Favourite() {
    }

    public Favourite(String placeId, Double rating) {
        this.placeId = placeId;
        this.rating = rating;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
