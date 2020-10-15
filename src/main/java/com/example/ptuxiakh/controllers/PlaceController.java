package com.example.ptuxiakh.controllers;


import com.example.ptuxiakh.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    @Autowired
    PlaceService placeService;

    @GetMapping("/all")
    public ResponseEntity getAllPlaces(@RequestParam(required = false) String type) {
        try {
            if (type == null)
                return ResponseEntity.ok(placeService.getAllPlaces());

            return ResponseEntity.ok(placeService.getAllPlacesByType(type));
        } catch (NullPointerException exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }catch (IllegalArgumentException exc){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{placeId}")
    public ResponseEntity getSinglePlace(@PathVariable Long placeId) {
        try {
            return ResponseEntity.ok(placeService.getSinglePlace(placeId));
        } catch (NullPointerException exc) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
