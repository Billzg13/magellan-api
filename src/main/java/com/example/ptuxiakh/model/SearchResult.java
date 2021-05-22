package com.example.ptuxiakh.model;

/**
 * {'name': 'Radisson Blu Park Hotel Athens', 'correlation': 0.9999999999999999, 'placeId': 1}
 */

import com.example.ptuxiakh.model.PlacePackage.Place;

/**
 * THis class is going to be used a the result that the python returns 
 */
public class SearchResult {
    Long placeId;
    String name;
    Double correlation;
    Place place;

    public SearchResult() {
    }

    public SearchResult(Long placeId, String name, Double correlation, Place place) {
        this.placeId = placeId;
        this.name = name;
        this.correlation = correlation;
        this.place = place;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCorrelation() {
        return correlation;
    }

    public void setCorrelation(Double correlation) {
        this.correlation = correlation;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
