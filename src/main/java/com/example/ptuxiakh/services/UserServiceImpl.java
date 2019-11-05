package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.auth.User;
import com.example.ptuxiakh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;


    @Override
    public List<User> getAllUsers() {
        try{
            return userRepository.findAll();
        }catch (Exception exc ){
            exc.printStackTrace();
            return null;
        }
    }

    @Override
    public User getSingleUser(String userId) {
        if (userId == null)
            throw new NullPointerException("userId cannot be null");

        User result = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("cant find user"));
        return result;
    }

    @Override
    public Boolean deleteUser(String userId) {
        if (userId == null)
            throw new NullPointerException("userId cannot be null");

        userRepository.deleteById(userId);
        return true;
    }

    @Override
    public User updateUser(String userId, User updateUser) {
        if (userId == null)
            throw new NullPointerException("userId cannot be null");

        if (updateUser == null)
            throw new NullPointerException("updateUser cannot be null");

        User result = userRepository.save(updateUser);
        return result;
    }
}
