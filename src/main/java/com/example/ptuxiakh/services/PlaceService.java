package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.PlacePackage.Place;
import com.example.ptuxiakh.model.PlacePackage.PlaceProjection;

import java.util.List;

public interface PlaceService {
    List<PlaceProjection> getAllPlacesByType(String type);
    List<PlaceProjection> getAllPlaces();
    Place getSinglePlace(String placeId);
}
