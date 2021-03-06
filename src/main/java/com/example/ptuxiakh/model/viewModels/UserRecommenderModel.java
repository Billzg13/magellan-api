package com.example.ptuxiakh.model.viewModels;

import com.example.ptuxiakh.model.auth.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Document(collection = "userRecommender")
public class UserRecommenderModel {
    /*
    gender: 0 || male 0, female 1
age: 24 || 13 - 100
price_level: 4 || 0 - 5
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

    @Id
    private String id;

    private String userId;

    @JsonIgnore
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

    Long placeId;

    public UserRecommenderModel() {
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setData(User user, Long placeId){
        this.placeId = placeId; //ccause this is Long
        this.age =user.getAge();
        setGender(user.getGender());
        this.priceLevel = 1;


        this.restaurant = user.getTypes().getRestaurant();
        this.bar = user.getTypes().getBar();
        this.food = user.getTypes().getFood();
        this.establishment = user.getTypes().getEstablishment();
        this.gym = user.getTypes().getGym();
        this.pointOfInterest = user.getTypes().getLodging();
        this.lodging = user.getTypes().getLodging();
        this.cafe = user.getTypes().getCafe();
        this.health = user.getTypes().getHealth();
    }

}
