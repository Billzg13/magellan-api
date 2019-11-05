package com.example.ptuxiakh.controllers;


import com.example.ptuxiakh.model.ErrorResponse;
import com.example.ptuxiakh.model.auth.User;
import com.example.ptuxiakh.repository.UserRepository;
import com.example.ptuxiakh.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * This controller is used for user crud purposes
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/all")
    public ResponseEntity getAllUsers(){
        try{
            return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
        }catch (Exception exc){
            exc.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/single/{userId}")
    public ResponseEntity getSingleUser(@PathVariable String userId){
        if (userId == null )
            return new ResponseEntity(new ErrorResponse("no user is selected"), HttpStatus.BAD_REQUEST);
        try{
            User result = userService.getSingleUser(userId);
            if (result != null)
                return new ResponseEntity(result, HttpStatus.OK);
            return new ResponseEntity(new ErrorResponse("user cannot be found"), HttpStatus.NOT_FOUND);
        }catch (Exception exc){
            exc.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
