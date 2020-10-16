package com.example.ptuxiakh.model.SolidSearch;

import com.example.ptuxiakh.model.PlacePackage.Location;

public class AdvancedSearchRequest {
    int radius;
    String category;
    int price;
    int rating;
    Location center;

    public AdvancedSearchRequest(int radius, String category, int price, int rating, Location center) {
        this.radius = radius;
        this.category = category;
        this.price = price;
        this.rating = rating;
        this.center = center;
    }

    public AdvancedSearchRequest() {
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Location getCenter() {
        return center;
    }

    public void setCenter(Location center) {
        this.center = center;
    }

    @Override
    public String toString() {
        return "AdvancedSearchRequest{" +
                "radius=" + radius +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", center=" + center +
                '}';
    }
}
