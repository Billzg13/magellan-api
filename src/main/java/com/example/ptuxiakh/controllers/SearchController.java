package com.example.ptuxiakh.controllers;

import com.example.ptuxiakh.model.QuickSearchResult;
import com.example.ptuxiakh.model.SolidSearch.QuickSearchResponse;
import com.example.ptuxiakh.model.errors.ErrorResponse;
import com.example.ptuxiakh.security.JwtTokenProvider;
import com.example.ptuxiakh.services.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity quickSearch(HttpServletRequest request){
        logger.debug("In action: quickSearch");
        try{
            String token = request.getHeader("Authorization").substring(7);
            String userId = tokenProvider.extractUserIdFromJwt(token);
            QuickSearchResponse response = searchService.quickSearch(userId);
            return new ResponseEntity<>(
                    new QuickSearchResult(
                            searchService.saveSearch(userId, response)
                    ),
                    HttpStatus.OK
            );
        }catch (NullPointerException exc){
            exc.printStackTrace();
            return new ResponseEntity(new ErrorResponse(exc.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (Exception exc) {
            exc.printStackTrace();
            return new ResponseEntity(new ErrorResponse(exc.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/quick/user")
    public ResponseEntity getLatestQuickSearches(HttpServletRequest request){
        logger.debug("In action: quickSearch");
        try{
            String token = request.getHeader("Authorization").substring(7);
            String userId = tokenProvider.extractUserIdFromJwt(token);
            return ResponseEntity.ok(searchService.getLatestQuickSearches(userId));
        }catch (NullPointerException exc){
            exc.printStackTrace();
            return new ResponseEntity(new ErrorResponse(exc.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (Exception exc) {
            return new ResponseEntity(new ErrorResponse(exc.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/quick/search")
    public ResponseEntity getLatestQuickSearches(HttpServletRequest request, @RequestParam String searchId){
        logger.debug("In action: quickSearch");
        try{
            return ResponseEntity.ok(searchService.findQuickSearchById(searchId));
        }catch (NullPointerException exc){
            exc.printStackTrace();
            return new ResponseEntity(new ErrorResponse(exc.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (Exception exc) {
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
    /**
     * the new advanced search route
     * @param request
     * @param advancedSearch
     * @return
     */
    @PostMapping("/advanced")
    public  ResponseEntity advancedSearch(HttpServletRequest request, @RequestBody com.example.ptuxiakh.model.SolidSearch.AdvancedSearchRequest advancedSearch){
        logger.debug("In action: advancedSearch "+advancedSearch.toString());
        try{
            if (advancedSearch == null)
                return new ResponseEntity(new Error("no search provided"), HttpStatus.BAD_REQUEST);
            String token = request.getHeader("Authorization").substring(7);
            String userId = tokenProvider.extractUserIdFromJwt(token);

            return ResponseEntity.ok( searchService.advancedSearch(userId, advancedSearch));
        }catch (Exception exc){
            return new ResponseEntity(new Error(exc.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
