package com.example.ptuxiakh.model.PlacePackage;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Projection of Place model
 */
public class PlaceProjection {
    String name;

    Double rating;

    String id;

    String vicinity;

    @JsonProperty("opening_hours")
    OpeningHours openingHours;

    @JsonProperty("price_level")
    int priceLevel;

    String phone;

    //add vicinity,opening hours, price level, <-- these exist in the database
    //fake phone

    public PlaceProjection() {
    }

    public PlaceProjection(Place place) {
        try {
            this.rating = place.getRating();
            this.name = place.getName();
            this.id = place.getId();
            this.vicinity = place.getVicinity();
            this.openingHours = place.getOpeningHours();
            this.priceLevel = place.getPriceLevel();
        }catch (Exception exc){
            exc.printStackTrace();
        }
        phone = "210-9058679";
    }

    public PlaceProjection(String name, Double rating, String id) {
        this.name = name;
        this.rating = rating;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public int getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(int priceLevel) {
        this.priceLevel = priceLevel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
