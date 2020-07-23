package com.example.ptuxiakh.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/*
restaurant: 0 - 5
lodging: 0 - 5
food: 0 - 5
point_of_interest: 0 - 5
establishment: 0 - 5
bar: 0 - 5
cafe: 0 - 5
health: 0 - 5
gym: 0 - 5
 */
public class Types {
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

    //Default constructor give 0 to all types
    public Types() {
        this.restaurant = 0;
        this.lodging = 0;
        this.food = 0;
        this.pointOfInterest = 0;
        this.establishment = 0;
        this.bar = 0;
        this.cafe = 0;
        this.health = 0;
        this.gym = 0;
    }

    public Types(int restaurant, int lodging, int food, int pointOfInterest, int establishment, int bar, int cafe, int health, int gym) {
        this.restaurant = restaurant;
        this.lodging = lodging;
        this.food = food;
        this.pointOfInterest = pointOfInterest;
        this.establishment = establishment;
        this.bar = bar;
        this.cafe = cafe;
        this.health = health;
        this.gym = gym;
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

    @Override
    public String toString() {
        return "Types{" +
                "restaurant=" + restaurant +
                ", lodging=" + lodging +
                ", food=" + food +
                ", pointOfInterest=" + pointOfInterest +
                ", establishment=" + establishment +
                ", bar=" + bar +
                ", cafe=" + cafe +
                ", health=" + health +
                ", gym=" + gym +
                '}';
    }
}
