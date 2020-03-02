package com.example.ptuxiakh.model.PlacePackage;

public class Northeast {
    Double lat;
    Double lng;

    public Northeast() {
    }

    public Northeast(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Northeast{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
