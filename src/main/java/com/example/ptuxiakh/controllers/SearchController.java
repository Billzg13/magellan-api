package com.example.ptuxiakh.controllers;

import com.example.ptuxiakh.model.AdvancedSearch;
import com.example.ptuxiakh.security.JwtTokenProvider;
import com.example.ptuxiakh.services.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    SearchService searchService;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/quick")
    public ResponseEntity quickSearch(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization").substring(7);
            String userId = tokenProvider.extractUserIdFromJwt(token);
            Object result = searchService.quickSearh(userId);
            return ResponseEntity.ok(result);

        } catch (Exception exc) {
            exc.printStackTrace();
            return new ResponseEntity(new Error("something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/advanced")
    public ResponseEntity advancedSearch(HttpServletRequest request, AdvancedSearch advancedSearch){
        try{
            if (advancedSearch == null)
                return new ResponseEntity(new Error("no search provided"), HttpStatus.BAD_REQUEST);
            String token = request.getHeader("Authorization").substring(7);
            String userId = tokenProvider.extractUserIdFromJwt(token);
            Object result = searchService.advancedSearch(userId, advancedSearch);
            return ResponseEntity.ok(result);

        }catch (Exception exc){
            exc.printStackTrace();
            return new ResponseEntity(new Error("something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
