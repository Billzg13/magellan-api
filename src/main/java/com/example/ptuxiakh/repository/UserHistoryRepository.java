package com.example.ptuxiakh.repository;

import com.example.ptuxiakh.model.UserHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserHistoryRepository extends MongoRepository<UserHistory, String > {

    List<UserHistory> findAllById(String userId);
}
