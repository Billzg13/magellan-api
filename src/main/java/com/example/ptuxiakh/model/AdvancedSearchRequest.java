package com.example.ptuxiakh.model;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class AdvancedSearchRequest {

    String gender;

    int genderNumeric;

    int age;

    @Min(0)
    @Max(5)
    int priceLevel; // 0 - 5

    @Min(0)
    @Max(5)
    int restaurant;

    @Min(0)
    @Max(5)
    int lodging;

    @Min(0)
    @Max(5)
    int food;

    @Min(0)
    @Max(5)
    int pointOfInterest;

    @Min(0)
    @Max(5)
    int establishment;

    @Min(0)
    @Max(5)
    int bar;

    @Min(0)
    @Max(5)
    int cafe;

    @Min(0)
    @Max(5)
    int health;

    @Min(0)
    @Max(5)
    int gym;


    public AdvancedSearchRequest() {
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        if (gender == "male")
            this.genderNumeric = 0;
        else if (gender == "female")
            this.genderNumeric = 1;
        else
            this.genderNumeric = 2; //in case "other" is selected
    }

    public int getGenderNumeric() {
        return genderNumeric;
    }

    public void setGenderNumeric(int genderNumeric) {
        this.genderNumeric = genderNumeric;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(int priceLevel) {
        this.priceLevel = priceLevel;
    }

    public int getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(int restaurant) {
        this.restaurant = restaurant;
    }

    public int getLodging() {
        return lodging;
    }

    public void setLodging(int lodging) {
        this.lodging = lodging;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getPointOfInterest() {
        return pointOfInterest;
    }

    public void setPointOfInterest(int pointOfInterest) {
        this.pointOfInterest = pointOfInterest;
    }

    public int getEstablishment() {
        return establishment;
    }

    public void setEstablishment(int establishment) {
        this.establishment = establishment;
    }

    public int getBar() {
        return bar;
    }

    public void setBar(int bar) {
        this.bar = bar;
    }

    public int getCafe() {
        return cafe;
    }

    public void setCafe(int cafe) {
        this.cafe = cafe;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getGym() {
        return gym;
    }

    public void setGym(int gym) {
        this.gym = gym;
    }


}
