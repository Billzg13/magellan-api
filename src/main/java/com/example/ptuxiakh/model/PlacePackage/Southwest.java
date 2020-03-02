package com.example.ptuxiakh.model.PlacePackage;

public class Southwest {
    Double lat;
    Double lng;

    public Southwest() {
    }

    public Southwest(Double lat, Double lng) {
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
        return "Southwest{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
