package com.example.ptuxiakh.model.viewModels;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;

public class UserViewModel {
    ArrayList<String> favourites;

    int age;

    String gender;

    @Size(max = 40)
    @Email
    String email;


    public UserViewModel(ArrayList<String> favourites, int age, String gender, @Size(max = 40) @Email String email) {
        this.favourites = favourites;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }

    public UserViewModel() {
    }

    public ArrayList<String> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<String> favourites) {
        this.favourites = favourites;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserViewModel{" +
                "favourites=" + favourites +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
