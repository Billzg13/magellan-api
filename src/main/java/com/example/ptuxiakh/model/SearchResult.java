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
    Long correlationWith;

    public SearchResult() {
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

    public Long getCorrelationWith() {
        return correlationWith;
    }

    public void setCorrelationWith(Long correlationWith) {
        this.correlationWith = correlationWith;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "placeId=" + placeId +
                ", name='" + name + '\'' +
                ", correlation=" + correlation +
                ", place=" + place +
                ", correlationWith=" + correlationWith +
                '}';
    }
}
