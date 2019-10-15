package com.example.ptuxiakh.repository;

import com.example.ptuxiakh.model.auth.Role;
import com.example.ptuxiakh.model.auth.RoleName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(RoleName roleName);
}
