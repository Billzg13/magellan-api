package com.example.ptuxiakh.model;

import com.example.ptuxiakh.model.PlacePackage.Place;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class FavouriteFull {
    Place place;

    @Min(0)
    @Max(5)
    int rating;

    public FavouriteFull() {
    }

    public FavouriteFull(Place place, @Min(0) @Max(5) int rating) {
        this.place = place;
        this.rating = rating;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "FavouriteFull{" +
                "place=" + place +
                ", rating=" + rating +
                '}';
    }
}
