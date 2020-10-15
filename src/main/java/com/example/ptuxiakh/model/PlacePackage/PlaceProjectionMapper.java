package com.example.ptuxiakh.model.PlacePackage;

import java.util.List;

public interface PlaceProjectionMapper {

    List<PlaceProjection> toListPlaceProjection(List<Place> places);

    PlaceProjection toPlaceProjection(Place place);
}
