package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.Favourite;
import com.example.ptuxiakh.model.FavouriteFull;
import com.example.ptuxiakh.model.auth.User;
import com.example.ptuxiakh.model.viewModels.UserRecommenderModel;

import java.util.List;

public interface UserService {

    User updateUserFinal(String userId, User user); //TODO make this the only updateUser function

    Boolean getFirstTimer(String userId);

    List<UserRecommenderModel> getAllUsersRecommender();

    List<Favourite> getFavouritesSlim(String userId);

    List<FavouriteFull> getFavourites(String userId);
}
