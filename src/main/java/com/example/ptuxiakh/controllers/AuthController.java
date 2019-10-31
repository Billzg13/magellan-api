package com.example.ptuxiakh.controllers;

import com.example.ptuxiakh.model.auth.Role;
import com.example.ptuxiakh.model.auth.RoleName;
import com.example.ptuxiakh.model.auth.User;
import com.example.ptuxiakh.payload.ApiResponse;
import com.example.ptuxiakh.payload.JwtAuthenticationResponse;
import com.example.ptuxiakh.payload.LoginRequest;
import com.example.ptuxiakh.payload.SignUpRequest;
import com.example.ptuxiakh.repository.RoleRepository;
import com.example.ptuxiakh.repository.UserRepository;
import com.example.ptuxiakh.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Date;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        System.out.println(signUpRequest.toString());
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User(signUpRequest.getFirstName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                signUpRequest.getLastName(), signUpRequest.getRole(), signUpRequest.getDateOfBirth(), signUpRequest.getGender());

        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));
        user.setDateCreated(new Date());
        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    @GetMapping("all/users")
    public ResponseEntity getAllUsers(){
        try{
            return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
        }catch (Exception exc){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId){
        if (userId.isEmpty())
            return new ResponseEntity<Error>(new Error("userId is empty"), HttpStatus.BAD_REQUEST);

        userRepository.deleteById(userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/edit/{userId}")
    public ResponseEntity editUser(@PathVariable String userId, @RequestBody User user){
        try {
            if (userId.isEmpty() || user.equals(null)) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            User result = userRepository.save(user);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (Exception exc){
            exc.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
