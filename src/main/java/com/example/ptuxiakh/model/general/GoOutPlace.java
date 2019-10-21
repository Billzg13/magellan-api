package com.example.ptuxiakh.model.general;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;

public class GoOutPlace {

    @Id
    String id;

    String name;

    //location of the bar
    String location;

    Date dateAdded;

    //lowest price of the place
    Double lowPrice;
    //most expensive price of the place
    Double highPrice;

    int population;

    String description;

    String category;

    ArrayList<String> tags;

    ArrayList<Integer> allRatings;

    Double finalRating;

    int averageAge;

    public GoOutPlace() {
    }

    public GoOutPlace(String id, String name, String location, Date dateAdded, Double lowPrice,
                      Double highPrice, int population, String description, String category,
                      ArrayList<String> tags, ArrayList<Integer> allRatings, Double finalRating, int averageAge) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.dateAdded = dateAdded;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.population = population;
        this.description = description;
        this.category = category;
        this.tags = tags;
        this.allRatings = allRatings;
        this.finalRating = finalRating;
        this.averageAge = averageAge;
    }

    public int getAverageAge() {
        return averageAge;
    }

    public void setAverageAge(int averageAge) {
        this.averageAge = averageAge;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<Integer> getAllRatings() {
        return allRatings;
    }

    public void setAllRatings(ArrayList<Integer> allRatings) {
        this.allRatings = allRatings;
    }

    public Double getFinalRating() {
        return finalRating;
    }

    public void setFinalRating(Double finalRating) {
        this.finalRating = finalRating;
    }
}
