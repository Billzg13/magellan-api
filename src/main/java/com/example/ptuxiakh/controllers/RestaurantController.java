package com.example.ptuxiakh.controllers;

import com.example.ptuxiakh.model.general.Restaurant;
import com.example.ptuxiakh.services.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/places")
public class RestaurantController {

    Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/all")
    public ResponseEntity getAllPlaces(){
        logger.debug("In Action getAllPlaces");
        try{
            return new ResponseEntity(restaurantService.getAllPlaces(), HttpStatus.OK);
        }catch (Exception exc){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{placeId}")
    public ResponseEntity getSinglePlace(@NotNull @PathVariable String placeId){
        logger.debug("In Action getSinglePlace with id: "+placeId);

        try{
            if (placeId != null)
                return new ResponseEntity(restaurantService.getSinglePlace(placeId), HttpStatus.OK);
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{placeId}")
    public ResponseEntity deleteSinglePlace(@NotNull @PathVariable String placeId){
        logger.debug("In Action deleteSinglePlace with id: "+placeId);

        try {
           if (placeId != null) {
                restaurantService.deletePlace(placeId);
           }
       }catch (Exception exc){
           exc.printStackTrace();
       }
        return null;
    }

    @PostMapping("/new")
    public ResponseEntity createNewPlace(@RequestBody Restaurant restaurant){
        logger.debug("In Action createNewPlace");

        try{
            if (restaurant != null) {
                Restaurant result = restaurantService.createNewPlace(restaurant);
                return new ResponseEntity(result, HttpStatus.OK);
            }
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update/{placeId}")
    public ResponseEntity updatePlace(@NotNull @PathVariable String placeId, @RequestBody Restaurant restaurant){
        logger.debug("In Action updatePlace with id: "+placeId);

        try{
            if (placeId != null && restaurant != null) {
                Restaurant result = restaurantService.updatePlace(placeId, restaurant);
                if (result != null)
                    return new ResponseEntity(result, HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception exc){
            exc.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
