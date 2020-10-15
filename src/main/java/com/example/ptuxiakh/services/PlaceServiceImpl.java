package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.PlacePackage.Place;
import com.example.ptuxiakh.model.PlacePackage.PlaceMapper;
import com.example.ptuxiakh.model.PlacePackage.PlaceProjection;
import com.example.ptuxiakh.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    PlaceRepository placeRepository;

    PlaceMapper placeMapper = new PlaceMapper();

    @Override
    public List<PlaceProjection> getAllPlacesByType(String type) {
        if (type == null || type.isEmpty())
            throw new NullPointerException("type is empty");

        return placeMapper.toListPlaceProjection(placeRepository.findAllByTypes(type));
    }

    @Override
    public List<PlaceProjection> getAllPlaces() {
        List<Place> places = placeRepository.findAll();
        return placeMapper.toListPlaceProjection(places);
    }

    @Override
    public Place getSinglePlace(Long placeId) {
        if (placeId == null)
            throw new NullPointerException("placeId is null");
        return placeRepository.findById(placeId).orElseThrow(()-> new NullPointerException("cant find place"));
    }


}
