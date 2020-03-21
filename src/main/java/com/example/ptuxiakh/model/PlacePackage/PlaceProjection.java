package com.example.ptuxiakh.model.PlacePackage;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

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

    ArrayList<String> types;

    //add vicinity,opening hours, price level, <-- these exist in the database
    //fake phone

    public PlaceProjection() {
    }

    public PlaceProjection(Place place) {
        try {

            this.types = place.getTypes();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        try {
            this.openingHours = place.getOpeningHours();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        try {
            this.vicinity = place.getVicinity();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        try {
            this.rating = place.getRating();
            this.name = place.getName();
            this.id = place.getId();
            this.priceLevel = place.getPriceLevel();
        } catch (Exception exc) {
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

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }
}
