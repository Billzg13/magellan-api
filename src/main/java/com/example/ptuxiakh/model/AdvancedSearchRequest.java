package com.example.ptuxiakh.model;

import com.example.ptuxiakh.model.PlacePackage.Location;

public class AdvancedSearchRequest {
    int rating;
    int radius;
    Location center;
    String category;
    int price;


    public AdvancedSearchRequest() {
    }

    public AdvancedSearchRequest(int rating, int radius, Location center, String category, int price) {
        this.rating = rating;
        this.radius = radius;
        this.center = center;
        this.category = category;
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Location getCenter() {
        return center;
    }

    public void setCenter(Location center) {
        this.center = center;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
