package com.example.ptuxiakh.model.PlacePackage;


/**
 * Projection of Place model
 */
public class PlaceProjection {
    String name;
    Double rating;
    String id;

    public PlaceProjection() {
    }

    public PlaceProjection(Place place) {
        this.rating = place.getRating();
        this.name = place.getName();
        this.id = place.getId();
    }

    public PlaceProjection(String name, Double rating, String id) {
        this.name = name;
        this.rating = rating;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
