package com.example.ptuxiakh.controllers;

import com.example.ptuxiakh.model.Types;
import com.example.ptuxiakh.model.auth.Role;
import com.example.ptuxiakh.model.auth.RoleName;
import com.example.ptuxiakh.model.auth.User;
import com.example.ptuxiakh.model.errors.ErrorResponse;
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
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This controller is used for auth purposes
 * TODO refactor!
 */
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

    @PostMapping("/signIn/longExpiration")
    public ResponseEntity<?> authenticateLongUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateLongExpirationToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        //System.out.println(signUpRequest.toString());
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        signUpRequest.setUsername(signUpRequest.getEmail());

        User user = new User(signUpRequest.getFirstName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                signUpRequest.getLastName(), "user", signUpRequest.getDateOfBirth(), signUpRequest.getGender(), 0, new Types());

        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));
        user.setDateCreated(new Date());

        //make a function for this?
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(signUpRequest.getDateOfBirth());
        int year = calendar.get(Calendar.YEAR);
        calendar.setTime(new Date());
        int currentYear = calendar.get(Calendar.YEAR);
        user.setAge(currentYear - year);
        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
