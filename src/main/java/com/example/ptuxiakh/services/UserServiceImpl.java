package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.auth.User;
import com.example.ptuxiakh.model.viewModels.UserViewModel;
import com.example.ptuxiakh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     *  use this function to update User without password...
     * @param userId
     * @param updateUser
     * @return
     */
    @Override
    public User updateUserNoAuth(String userId, UserViewModel updateUser) {
        if (userId == null)
            throw new NullPointerException("user id is null");

        if (updateUser == null)
            throw new NullPointerException("update user data empty");

        User result = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("cant find user"));

        if (updateUser.getFavourites() != null)
            result.setFavourites(updateUser.getFavourites());
        if (updateUser.getAge() != 0)
            result.setAge(updateUser.getAge());
        if (updateUser.getEmail() != null)
            result.setEmail(updateUser.getEmail());
        if (updateUser.getGender() != null)
            result.setGender(result.getGender());
        System.out.println("this is the user:  "+result.toString());
        return userRepository.save(result);
    }

    @Override
    public User updateFavouritesUser(String userId, ArrayList<String> favourites) {
        if (userId == null || favourites.isEmpty() || favourites == null)
            throw new NullPointerException("favourites or user is not present");
        User user = userRepository.findById(userId).orElseThrow( ()-> new NullPointerException("User not found"));

        return null;
    }
}
