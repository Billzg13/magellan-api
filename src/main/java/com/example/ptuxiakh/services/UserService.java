package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.Favourite;
import com.example.ptuxiakh.model.auth.User;
import com.example.ptuxiakh.model.viewModels.UserRecommenderModel;
import com.example.ptuxiakh.model.viewModels.UserViewModel;

import java.util.ArrayList;
import java.util.List;
//TODO make 1 updateUser method for all
public interface UserService {
    public abstract List<User> getAllUsers();
    public abstract User getSingleUser(String userId);
    public abstract Boolean deleteUser(String userId);
    public abstract User updateUser(String userId, User updateUser); //will be deprecated
    public abstract User updateUserNoAuth(String userId, UserViewModel updateUser); //will be deprecated
    public User updateFavouritesUser(String userId, ArrayList<String> favourites); //Will be deprecated!
    public User updateUserFinal(String userId, User user); //TODO make this the only updateUser function
    Boolean getFirstTimer(String userId);
    public List<UserRecommenderModel> getAllUsersRecommender();

    ArrayList<Favourite> updateFavouritesForUser(String userId, ArrayList<Favourite> favourites); //will be deprecated!
}
