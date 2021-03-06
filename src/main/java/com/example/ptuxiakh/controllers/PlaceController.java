package com.example.ptuxiakh.controllers;


import com.example.ptuxiakh.services.PlaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceController {
    Logger logger = LoggerFactory.getLogger(PlaceController.class);

    @Autowired
    PlaceService placeService;

    @GetMapping("/all")
    public ResponseEntity getAllPlaces(
            @RequestParam(required = false) List<String> types,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNo) {
        try {
            if (types == null || types.isEmpty()){
                return ResponseEntity.ok(placeService.getAllPlaces(pageSize, pageNo));
            }
            return ResponseEntity.ok(placeService.getAllPlacesByTypes(types, pageSize, pageNo));
        } catch (NullPointerException exc) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
