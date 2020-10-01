package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.PlacePackage.Place;
import com.example.ptuxiakh.model.PlacePackage.PlaceProjection;
import com.example.ptuxiakh.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    PlaceRepository placeRepository;

    @Override
    public List<PlaceProjection> getAllPlacesByType(String type) {
        if (type == null)
            throw new NullPointerException("type is empty");
        List<Place> places = placeRepository.findAllByTypes(type);
        return places.stream().map(place -> {
            PlaceProjection placeProjection = new PlaceProjection(place);
            return placeProjection;
        }).collect(Collectors.toList());
    }

    @Override
    public List<PlaceProjection> getAllPlaces() {
        List<Place> places = placeRepository.findAll();
        return places.stream().map(place -> {
            PlaceProjection placeProjection = new PlaceProjection(place);
            return placeProjection;
        }).collect(Collectors.toList());
    }

    @Override
    public Place getSinglePlace(Long placeId) {
        if (placeId == null)
            throw new NullPointerException("placeId is null");
        return placeRepository.findById(placeId).orElseThrow(()-> new NullPointerException("cant find place"));
    }
}
