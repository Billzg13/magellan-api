package com.example.ptuxiakh.controllers;


import com.example.ptuxiakh.model.Favourite;
import com.example.ptuxiakh.model.FirstTimeLogin;
import com.example.ptuxiakh.model.auth.User;
import com.example.ptuxiakh.model.errors.ErrorResponse;
import com.example.ptuxiakh.model.viewModels.UpdateFavouritesRequest;
import com.example.ptuxiakh.model.viewModels.UserViewModel;
import com.example.ptuxiakh.security.JwtTokenProvider;
import com.example.ptuxiakh.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


/**
 * This controller is used for user crud purposes
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider tokenProvider;

    //TODO why do we need this?
    @GetMapping("/all")
    public ResponseEntity getAllUsers() {
        try {
            List<User> result = userService.getAllUsers();
            System.out.println(result);
            return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception exc) {
            exc.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/recommender")
    public ResponseEntity getRecommenderData(){
        try{
            return new ResponseEntity(userService.getAllUsersRecommender(), HttpStatus.OK);
        }catch (Exception exc) {
            exc.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recommender2")
    public ResponseEntity getRecommenderData2(){
        try{
            return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
        }catch (Exception exc){
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


    @GetMapping("/me")
    public ResponseEntity getSingleUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String userId = tokenProvider.extractUserIdFromJwt(token);
        if (userId == null)
            return new ResponseEntity(new ErrorResponse("no user is selected"), HttpStatus.BAD_REQUEST);
        try {
            User result = userService.getSingleUser(userId);
            if (result != null)
                return new ResponseEntity(result, HttpStatus.OK);
            return new ResponseEntity(new ErrorResponse("user cannot be found"), HttpStatus.NOT_FOUND);
        } catch (Exception exc) {
            exc.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This is the final update User, all other update users will be removed,and their models
     * The user model must have all the data!, user.id is not required
     * @param request
     * @param user
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity updateUser(HttpServletRequest request, @NotBlank @RequestBody User user){
        String token = request.getHeader("Authorization").substring(7);
        String userId = tokenProvider.extractUserIdFromJwt(token);
        try{
            if (user == null)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            User result = userService.updateUserFinal(userId, user);
            return ResponseEntity.ok(result);
        }catch (NullPointerException exc){
            return new ResponseEntity(new ErrorResponse("provide necesary data"), HttpStatus.BAD_REQUEST);
        }
    }


}
