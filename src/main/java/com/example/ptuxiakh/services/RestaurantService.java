package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.Place;

import java.util.List;

public interface RestaurantService {
    public abstract List<Place> getAllPlaces();
    public abstract Place getSinglePlace(String placeId);
    public abstract Boolean deletePlace(String placeId);
    public abstract Place createNewPlace(Place place);
    public abstract Place updatePlace(String placeId, Place place);
}
