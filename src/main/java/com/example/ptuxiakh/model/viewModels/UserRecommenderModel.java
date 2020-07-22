package com.example.ptuxiakh.model.viewModels;

import com.example.ptuxiakh.model.Types;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Document(collection = "user")
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

    String gender;

    int age;

    @Min(0)
    @Max(5)
    int priceLevel; // 0 - 5

    Types types;

    public UserRecommenderModel() {
    }

    public UserRecommenderModel(String id, String gender, int age, @Min(0) @Max(5) int priceLevel, Types types) {
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.priceLevel = priceLevel;
        this.types = types;
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

    public Types getTypes() {
        return types;
    }

    public void setTypes(Types types) {
        this.types = types;
    }
}
