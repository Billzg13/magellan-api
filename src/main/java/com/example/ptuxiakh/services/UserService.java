package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.auth.User;

import java.util.List;

public interface UserService {
    public abstract List<User> getAllUsers();
    public abstract User getSingleUser(String userId);
    public abstract Boolean deleteUser(String userId);
    public abstract User updateUser(String userId, User updateUser);

}
