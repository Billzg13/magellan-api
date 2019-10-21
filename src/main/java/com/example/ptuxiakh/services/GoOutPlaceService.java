package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.general.GoOutPlace;

import java.util.List;

public interface GoOutPlaceService {
    public abstract List<GoOutPlace> getAllPlaces();
    public abstract GoOutPlace getSinglePlace(String placeId);
    public abstract Boolean deletePlace(String placeId);
    public abstract GoOutPlace createNewPlace(GoOutPlace goOutPlace);
    public abstract GoOutPlace updatePlace(String placeId, GoOutPlace goOutPlace);
}
