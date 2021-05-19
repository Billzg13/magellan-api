package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.PlacePackage.Place;
import com.example.ptuxiakh.model.PlacePackage.PlaceProjection;

import java.util.List;

public interface PlaceService {
    List<PlaceProjection> getAllPlacesByTypes(List<String> types, Integer pageSize, Integer pageNo);
    List<PlaceProjection> getAllPlaces(Integer pageSize, Integer pageNo);
    Place getSinglePlace(Long placeId);
}
