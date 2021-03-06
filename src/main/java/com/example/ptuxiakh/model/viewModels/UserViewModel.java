package com.example.ptuxiakh.model.viewModels;

import com.example.ptuxiakh.model.Favourite;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;

public class UserViewModel {
    ArrayList<Favourite> favourites;

    int age;

    String gender;

    @Size(max = 40)
    @Email
    String email;


    public UserViewModel(ArrayList<Favourite> favourites, int age, String gender, @Size(max = 40) @Email String email) {
        this.favourites = favourites;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }

    public UserViewModel() {
    }

    public ArrayList<Favourite> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<Favourite> favourites) {
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
