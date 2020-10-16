package com.example.ptuxiakh.controllers;


import com.example.ptuxiakh.model.FirstTimeLogin;
import com.example.ptuxiakh.model.auth.User;
import com.example.ptuxiakh.model.errors.ErrorResponse;
import com.example.ptuxiakh.security.JwtTokenProvider;
import com.example.ptuxiakh.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;


/**
 * This controller is used for user crud purposes
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider tokenProvider;


    @GetMapping("/recommender")
    public ResponseEntity getRecommenderData() {
        try {
            return new ResponseEntity(userService.getAllUsersRecommender(), HttpStatus.OK);
        } catch (Exception exc) {
            exc.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/first")
    public ResponseEntity getFirstTime(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String userId = tokenProvider.extractUserIdFromJwt(token);
        //if userService.getFirstTimer returns true then its first time user else its
        if (userService.getFirstTimer(userId))
            return ResponseEntity.ok(new FirstTimeLogin(Boolean.TRUE));

        return ResponseEntity.ok(new FirstTimeLogin(Boolean.FALSE));
    }

    /**
     * used only on the first update for favourites only
     *
     * @param request
     * @param user
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity updateUser(HttpServletRequest request, @NotBlank @RequestBody User user) {
        String token = request.getHeader("Authorization").substring(7);
        String userId = tokenProvider.extractUserIdFromJwt(token);
        System.out.println("in api/users/update");
        try {
            if (user == null)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            User result = userService.updateUserFinal(userId, user);
            return ResponseEntity.ok(result);
        } catch (NullPointerException exc) {
            exc.printStackTrace();
            return new ResponseEntity(new ErrorResponse("provide necesary data"), HttpStatus.BAD_REQUEST);
        }
    }


}
