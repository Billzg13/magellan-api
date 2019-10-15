package com.example.ptuxiakh.seeder;


import com.example.ptuxiakh.model.auth.Role;
import com.example.ptuxiakh.model.auth.RoleName;
import com.example.ptuxiakh.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class DatabaseSeeder {

    private RoleRepository repository;
    Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

    @Autowired
    public DatabaseSeeder(
            RoleRepository roleRepository) {
        this.repository = roleRepository;
    }
    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedRolesTable();
    }

    private void seedRolesTable(){

        Optional<Role> roleExists = repository.findByName(RoleName.ROLE_ADMIN);

        if (!roleExists.isPresent()) {

            Role admin = new Role();
            admin.setName(RoleName.ROLE_ADMIN);

            Role user = new Role();
            user.setName(RoleName.ROLE_USER);

            try {
                repository.save(admin);
                repository.save(user);

            } catch (Exception ex) {
                logger.debug(ex.getMessage());
            }
        }
    }
}
