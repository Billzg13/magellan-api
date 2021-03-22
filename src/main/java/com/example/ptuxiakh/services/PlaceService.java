package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.PlacePackage.Place;
import com.example.ptuxiakh.model.PlacePackage.PlaceProjection;

import java.util.List;

public interface PlaceService {
    List<PlaceProjection> getAllPlacesByTypes(List<String> types, int pageSize, int pageNo);
    List<PlaceProjection> getAllPlaces(int pageSize, int pageNo);
    Place getSinglePlace(Long placeId);
}
