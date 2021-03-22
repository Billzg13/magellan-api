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
        System.out.println("In updateUserFinal");

        User result = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("cant find user"));
        if (user.getFavourites() != null)
            result.setFavourites(user.getFavourites());

        Types types = new Types();
        types.setBar(1);
        types.setRestaurant(1);
        types.setPointOfInterest(1);
        types.setHealth(1);
        types.setFood(1);
        types.setLodging(1);
        types.setEstablishment(1);
        types.setCafe(1);
        types.setGym(1);

        result.setTypes(types);
        result = userRepository.save(result);


        User finalResult = result;

        for (Favourite favourite : result.getFavourites()) {
            System.out.println("in for loop");
            System.out.println(favourite.toString());
            //each favourite has an placeId
            Place place = placeRepository.findById(favourite.getPlaceId()).orElseThrow(() -> new RuntimeException("cant find place"));
            UserRecommenderModel userRecommenderModel = new UserRecommenderModel();
            userRecommenderModel.setUserId(userId);
            userRecommenderModel.setData(finalResult, favourite.getPlaceId()); //sets all data for usserRecommender Model
            System.out.println("before save: " + userRecommenderModel.toString());
            userRecommenderRepository.save(userRecommenderModel);
        }

        return result;
    }
}
