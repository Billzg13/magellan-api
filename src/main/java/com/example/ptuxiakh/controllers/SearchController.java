package com.example.ptuxiakh.controllers;

import com.example.ptuxiakh.model.AdvancedSearchRequest;
import com.example.ptuxiakh.model.errors.ErrorResponse;
import com.example.ptuxiakh.security.JwtTokenProvider;
import com.example.ptuxiakh.services.SearchService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    //SYNCHRONOUS
    @PostMapping("/quick")
    public ResponseEntity quickSearch(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization").substring(7);
            String userId = tokenProvider.extractUserIdFromJwt(token);

            ResponseEntity result = searchService.quickSearh(userId);
            result.getBody();
            return ResponseEntity.ok(result.getBody());

        }catch (NullPointerException exc){
            exc.printStackTrace();
            return new ResponseEntity(new Error(exc.getMessage()), HttpStatus.BAD_REQUEST);
        }
        catch (Exception exc) {
            exc.printStackTrace();
            return new ResponseEntity(new Error("something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/quick2")
    public ResponseEntity quickSearch2(HttpServletRequest request, Authorization authorization){
        System.out.println(authorization.toString());
        try{
            String token = request.getHeader("Authorization").substring(7);
            String userId = tokenProvider.extractUserIdFromJwt(token);

            Object result = searchService.quickSearch2(userId);
            if (result == null){
                return new ResponseEntity(new ErrorResponse("yo s"),HttpStatus.BAD_REQUEST);
                //return new ResponseEntity(new Error("cant find any recommendation"), HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(result);
        }catch (NullPointerException exc){
            exc.printStackTrace();
            return new ResponseEntity(new ErrorResponse(exc.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (Exception exc) {
            //exc.printStackTrace();
            System.out.println("i am in here");
            return new ResponseEntity(new ErrorResponse(exc.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //advanced model:
    /*
    int rating
    int radius
    Object center{
        Double latitude
        Double longtitude
    }
    String category
    int price
     */
    @PostMapping("/advanced")
    public ResponseEntity advancedSearch(HttpServletRequest request, @RequestBody AdvancedSearchRequest advancedSearchRequest){
        try{
            if (advancedSearchRequest == null)
                return new ResponseEntity(new Error("no search provided"), HttpStatus.BAD_REQUEST);
            String token = request.getHeader("Authorization").substring(7);
            String userId = tokenProvider.extractUserIdFromJwt(token);
            Object result = searchService.advancedSearch(userId, advancedSearchRequest);
            return ResponseEntity.ok(result);

        }catch (Exception exc){
            exc.printStackTrace();
            return new ResponseEntity(new Error("something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * the new advanced search route
     * @param request
     * @param advancedSearch
     * @return
     */
    @PostMapping("/advanced2")
    public  ResponseEntity advancedSearch2(HttpServletRequest request, @RequestBody com.example.ptuxiakh.model.SolidSearch.AdvancedSearchRequest advancedSearch){
        try{
            if (advancedSearch == null)
                return new ResponseEntity(new Error("no search provided"), HttpStatus.BAD_REQUEST);
            String token = request.getHeader("Authorization").substring(7);
            String userId = tokenProvider.extractUserIdFromJwt(token);

            return ResponseEntity.ok( searchService.advancedSearch2(userId, advancedSearch));
        }catch (Exception exc){
            return new ResponseEntity(new Error("something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
