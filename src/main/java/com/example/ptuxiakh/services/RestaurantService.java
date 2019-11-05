package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.general.Restaurant;

import java.util.List;

public interface RestaurantService {
    public abstract List<Restaurant> getAllPlaces();
    public abstract Restaurant getSinglePlace(String placeId);
    public abstract Boolean deletePlace(String placeId);
    public abstract Restaurant createNewPlace(Restaurant restaurant);
    public abstract Restaurant updatePlace(String placeId, Restaurant restaurant);
}
