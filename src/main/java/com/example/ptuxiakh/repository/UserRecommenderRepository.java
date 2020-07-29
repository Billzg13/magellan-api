package com.example.ptuxiakh.repository;

import com.example.ptuxiakh.model.viewModels.UserRecommenderModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRecommenderRepository extends MongoRepository<UserRecommenderModel, String> {
    List<UserRecommenderModel> findAllByUserId(String userId);
}
