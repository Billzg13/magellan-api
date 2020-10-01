package com.example.ptuxiakh.model.PlacePackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "places")
public class Place {
    @Id
    Long id;

    Geometry geometry;

    String icon;

    String name;

    @JsonProperty("opening_hours")
    OpeningHours openingHours;

    @JsonProperty("place_id")
    String placeId;

    Double rating;

    String reference;

    String scope;

    ArrayList<String> types;

    @JsonProperty("price_level")
    int priceLevel;

    @JsonProperty("user_ratings_total")
    long userRatingsTotal;

    String vicinity;

    public Place() {
    }

    public Place(Long id, Geometry geometry, String icon, String name, OpeningHours openingHours, String placeId, Double rating, String reference, String scope, ArrayList<String> types, long userRatingsTotal, String vicinity, int priceLevel) {
        this.id = id;
        this.geometry = geometry;
        this.icon = icon;
        this.name = name;
        this.openingHours = openingHours;
        this.placeId = placeId;
        this.rating = rating;
        this.reference = reference;
        this.scope = scope;
        this.types = types;
        this.userRatingsTotal = userRatingsTotal;
        this.vicinity = vicinity;
        this.priceLevel = priceLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public long getUserRatingsTotal() {
        return userRatingsTotal;
    }

    public void setUserRatingsTotal(long userRatingsTotal) {
        this.userRatingsTotal = userRatingsTotal;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public int getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(int priceLevel) {
        this.priceLevel = priceLevel;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                ", geometry=" + geometry +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", openingHours=" + openingHours +
                ", placeId='" + placeId + '\'' +
                ", rating=" + rating +
                ", reference='" + reference + '\'' +
                ", scope='" + scope + '\'' +
                ", types=" + types +
                ", userRatingsTotal=" + userRatingsTotal +
                ", vicinity='" + vicinity + '\'' +
                '}';
    }
}
