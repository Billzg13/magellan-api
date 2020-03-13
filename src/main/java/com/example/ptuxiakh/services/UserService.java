package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.auth.User;
import com.example.ptuxiakh.model.viewModels.UserViewModel;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    public abstract List<User> getAllUsers();
    public abstract User getSingleUser(String userId);
    public abstract Boolean deleteUser(String userId);
    public abstract User updateUser(String userId, User updateUser);
    public abstract User updateUserNoAuth(String userId, UserViewModel updateUser);
    public User updateFavouritesUser(String userId, ArrayList<String> favourites);

    Boolean getFirstTimer(String userId);
}
