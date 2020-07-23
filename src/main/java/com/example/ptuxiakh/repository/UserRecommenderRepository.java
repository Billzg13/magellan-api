package com.example.ptuxiakh.repository;

import com.example.ptuxiakh.model.viewModels.UserRecommenderModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRecommenderRepository extends MongoRepository<UserRecommenderModel, String> {

}
