package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.Favourite;
import com.example.ptuxiakh.model.FavouriteFull;
import com.example.ptuxiakh.model.PlacePackage.Place;
import com.example.ptuxiakh.model.Types;
import com.example.ptuxiakh.model.auth.User;
import com.example.ptuxiakh.model.viewModels.UserRecommenderModel;
import com.example.ptuxiakh.repository.PlaceRepository;
import com.example.ptuxiakh.repository.UserRecommenderRepository;
import com.example.ptuxiakh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRecommenderRepository userRecommenderRepository;

    @Autowired
    PlaceRepository placeRepository;


    /**
     * Returns true in case favourites are not null or empty
     *
     * @param userId
     * @return
     */
    @Override
    public Boolean getFirstTimer(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("cant find user"));
        try {
            if (user.getFavourites().size() >= 1)
                return false;
            return true;
        } catch (Exception exc) {
            return true;
        }

    }

    @Override
    public List<UserRecommenderModel> getAllUsersRecommender() {
        return userRecommenderRepository.findAll();
    }

    @Override
    public List<Favourite> getFavouritesSlim(String userId) {
        if (userId == null)
            throw new NullPointerException("cant update if something is null");
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("can't find user"));
        return user.getFavourites();
    }

    @Override
    public List<FavouriteFull> getFavourites(String userId) {
        if (userId == null)
            throw new NullPointerException("cant update if something is null");
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("can't find user"));
        if (user.getFavourites().isEmpty())
            return null;
        List<FavouriteFull> favourites = new ArrayList<>();
        for (Favourite favourite: user.getFavourites()){
            favourites.add(new FavouriteFull(
                    placeRepository.findById(favourite.getPlaceId()).
                            orElseThrow(()-> new RuntimeException("can't find place")),
                    favourite.getRating())
            );
        }
        return favourites;
    }

    @Override
    public User updateUserFinal(String userId, User user) {
        //The userId cannot be null, the user cannot be null
        if (userId == null || user == null)
            throw new NullPointerException("cant update if something is null");

        User result = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("cant find user"));
        if (user.getFavourites() != null)
            result.setFavourites(user.getFavourites());
        result = userRepository.save(result);
        return result;
    }
}
