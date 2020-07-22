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


    //TODO probably dont need this anymore UPDATE we need this but favourites will be removed
    @GetMapping("/first")
    public ResponseEntity getFirstTime(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String userId = tokenProvider.extractUserIdFromJwt(token);

        if (userService.getFirstTimer(userId))
            return ResponseEntity.ok(new FirstTimeLogin(Boolean.FALSE));

        return ResponseEntity.ok(new FirstTimeLogin(Boolean.TRUE));
    }

    //TODO change route to api/users/me
    @GetMapping("/single/{userId}")
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

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity updateUser(@PathVariable String userId, @RequestBody UserViewModel updateUser) {
        try {
            if (userId == null)
                return new ResponseEntity(new ErrorResponse("no user is provided"), HttpStatus.BAD_REQUEST);
            if (updateUser == null)
                return new ResponseEntity(new ErrorResponse("no update data is provided"), HttpStatus.BAD_REQUEST);

            User result = userService.updateUserNoAuth(userId, updateUser);

            if (result != null)
                return new ResponseEntity(result, HttpStatus.OK);

            return new ResponseEntity(new ErrorResponse("something went wrong"), HttpStatus.OK);

        } catch (Exception exc) {
            exc.printStackTrace();
            return new ResponseEntity(new ErrorResponse("something went wrong during update"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/favourites")
    public ResponseEntity updateFavourites(HttpServletRequest request, @RequestBody UpdateFavouritesRequest updateFavouritesRequest) {
        String token = request.getHeader("Authorization").substring(7);
        String userId = tokenProvider.extractUserIdFromJwt(token);
        try {
            if (updateFavouritesRequest == null || updateFavouritesRequest.getFavourites() == null) // <-- This will throw a null Exception!!!
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

            ArrayList<Favourite> result = userService.updateFavouritesForUser(userId, updateFavouritesRequest.getFavourites());
            if (result != null)
                return ResponseEntity.ok(result);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (NullPointerException exc){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch (Exception exc) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
