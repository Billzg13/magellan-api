package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.Favourite;
import com.example.ptuxiakh.model.PlacePackage.Place;
import com.example.ptuxiakh.model.Types;
import com.example.ptuxiakh.model.auth.User;
import com.example.ptuxiakh.model.viewModels.UserRecommenderModel;
import com.example.ptuxiakh.model.viewModels.UserViewModel;
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
            List<UserRecommenderModel> userData = userRecommenderRepository.findAllByUserId(userId);
            if (userData == null || userData.isEmpty()) {
                return true;
            }
        } catch (Exception exc) {
            return true;
        }
        return false;
    }

    @Override
    public List<UserRecommenderModel> getAllUsersRecommender() {
        return userRecommenderRepository.findAll();
    }

    @Override
    public ArrayList<Favourite> updateFavouritesForUser(String userId, ArrayList<Favourite> favourites) {
        if (userId == null)
            throw new NullPointerException("user is not provided");
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("cant find user"));
        //     user.setFavourites(favourites);
        userRepository.save(user);
        return favourites;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception exc) {
            exc.printStackTrace();
            return null;
        }
    }

    @Override
    public User getSingleUser(String userId) {
        if (userId == null)
            throw new NullPointerException("userId cannot be null");

        User result = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("cant find user"));
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
     * use this function to update User without password...
     *
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

        User result = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("cant find user"));

        if (updateUser.getFavourites() != null)
            //  result.setFavourites(updateUser.getFavourites());
            if (updateUser.getAge() != 0)
                result.setAge(updateUser.getAge());
        if (updateUser.getEmail() != null)
            result.setEmail(updateUser.getEmail());
        if (updateUser.getGender() != null)
            result.setGender(result.getGender());
        System.out.println("this is the user:  " + result.toString());
        return userRepository.save(result);
    }

    @Override
    public User updateFavouritesUser(String userId, ArrayList<String> favourites) {
        if (userId == null || favourites.isEmpty() || favourites == null)
            throw new NullPointerException("favourites or user is not present");
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("User not found"));

        return null;
    }

    @Override
    public User updateUserFinal(String userId, User user) {
        //The userId cannot be null, the user cannot be null
        if (userId == null || user == null)
            throw new NullPointerException("cant update if something is null");

        User result = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("cant find user"));
        if (user.getFavourites() != null)
            result.setFavourites(user.getFavourites());

        Types types = user.getTypes();
        types.setGym(types.getGym() + 1);

        result.setTypes(types);

        for (Favourite favourite : user.getFavourites()) {
            Place place = placeRepository.findById(favourite.getPlaceId()).orElseThrow(() -> new RuntimeException("cant find place"));

            for (String type : place.getTypes()) {
                switch (type) {
                    case "gym":
                        if (types.getGym() >= 5)
                            break;
                        types.setGym(types.getGym()+1);
                        break;
                    case "bar":
                        if (types.getBar() >= 5 )
                            break;
                        types.setBar(types.getBar()+1);
                        break;
                    case "cafe":
                        if (types.getCafe() >= 5 )
                            break;
                        types.setCafe(types.getCafe()+1);
                        break;
                    case "establishment":
                        if (types.getEstablishment() >= 5 )
                            break;
                        types.setEstablishment(types.getEstablishment()+1);
                        break;
                    case "food":
                        if (types.getFood() >= 5 )
                            break;
                        types.setFood(types.getFood()+1);
                        break;
                    case "lodging":
                        if (types.getLodging() >= 5 )
                            break;
                        types.setLodging(types.getLodging()+1);
                        break;
                    case "health":
                        if (types.getHealth() >= 5 )
                            break;
                        types.setHealth(types.getHealth()+1);
                        break;
                    case "point_of_interest":
                        if (types.getPointOfInterest() >= 5 )
                            break;
                        types.setPointOfInterest(types.getPointOfInterest()+1);
                        break;
                    case "restaurant":
                        if (types.getRestaurant() >= 5 )
                            break;
                        types.setRestaurant(types.getRestaurant()+1);
                        break;
                    default:
                        System.out.println("Looking forward to the Weekend");
                }
            }
        }
        UserRecommenderModel userRecommenderModel = new UserRecommenderModel();
        userRecommenderModel.setAge(result.getAge());
        userRecommenderModel.setGender(result.getGender());
        userRecommenderModel.setPlaceId(1); //this comes from the favourite and this is also int
        userRecommenderModel.setUserId(result.getId());

        //these can be added when favourites loop is completed
        userRecommenderModel.setBar(types.getBar());
        userRecommenderModel.setCafe(types.getCafe());
        userRecommenderModel.setEstablishment(types.getEstablishment());
        userRecommenderModel.setGym(types.getGym());
        userRecommenderModel.setHealth(types.getHealth());
        userRecommenderModel.setLodging(types.getLodging());
        userRecommenderModel.setPointOfInterest(types.getPointOfInterest());
        userRecommenderModel.setFood(types.getFood());
        userRecommenderModel.setRestaurant(types.getRestaurant());


        result.setTypes(types);
        userRecommenderRepository.insert(userRecommenderModel);
        //also update types here and then update userRecommenderModel

        return userRepository.save(result);
    }


}
