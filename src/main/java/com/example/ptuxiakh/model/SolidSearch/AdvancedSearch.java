package com.example.ptuxiakh.model.SolidSearch;

import com.example.ptuxiakh.model.PlacePackage.Location;
import org.springframework.http.ResponseEntity;

public class AdvancedSearch extends SearchRequest implements AdvancedRecommender {

    int radius;
    String category;
    int price;
    int rating;
    Location center;

    public AdvancedSearch() {
    }

    public AdvancedSearch(AdvancedSearchRequest advancedSearchRequest) {
        this.radius = advancedSearchRequest.getRadius();
        this.category = advancedSearchRequest.getCategory();
        this.price = advancedSearchRequest.getPrice();
        this.rating = advancedSearchRequest.getRating();
        this.center = advancedSearchRequest.getCenter();
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
    public ResponseEntity<Object> recommend() {
        return null;
    }
}
