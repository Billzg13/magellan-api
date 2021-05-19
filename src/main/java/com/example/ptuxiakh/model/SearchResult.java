package com.example.ptuxiakh.model;

/**
 * {'name': 'Radisson Blu Park Hotel Athens', 'correlation': 0.9999999999999999, 'placeId': 1}
 */

/**
 * THis class is going to be used a the result that the python returns 
 */
public class SearchResult {
    Long placeId;
    String name;
    Double correlation;

    public SearchResult() {
    }

    public SearchResult(Long placeId, String name, Double correlation) {
        this.placeId = placeId;
        this.name = name;
        this.correlation = correlation;
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
}
