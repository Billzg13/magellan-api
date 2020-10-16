package com.example.ptuxiakh.model.PlacePackage;

import java.util.List;
import java.util.stream.Collectors;

public class PlaceMapper implements PlaceProjectionMapper {

    @Override
    public List<PlaceProjection> toListPlaceProjection(List<Place> places) {
        if (places == null)
            throw new NullPointerException("places are null");
        if(places.isEmpty())
            return null;

        return places.stream().map(place -> {
            PlaceProjection placeProjection = new PlaceProjection(place);
            return placeProjection;
        }).collect(Collectors.toList());

    }

    @Override
    public PlaceProjection toPlaceProjection(Place place) {
        if (place == null)
            throw new NullPointerException("place is null");

        return new PlaceProjection(place);
    }

}
