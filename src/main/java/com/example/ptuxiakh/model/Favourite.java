package com.example.ptuxiakh.model;

public class Favourite {

    String placeId;

    int rating;

    public Favourite() {
    }

    public Favourite(String placeId, int rating) {
        this.placeId = placeId;
        this.rating = rating;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
