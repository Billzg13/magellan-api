package com.example.ptuxiakh.controllers;


import com.example.ptuxiakh.model.PlacePackage.Place;
import com.example.ptuxiakh.model.PlacePackage.PlaceProjection;
import com.example.ptuxiakh.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    @Autowired
    PlaceRepository placeRepository;

    @GetMapping("/all")
    public ResponseEntity getAllPlaces(@RequestParam(required = false) String type){

        List<Place> places = placeRepository.findAll();
        return ResponseEntity.ok(places.stream().map( place -> {
            PlaceProjection placeProjection = new PlaceProjection(place);
            return placeProjection;
        }).collect(Collectors.toList()));
        //return ResponseEntity.ok(placeRepository.findAll());

       // return null;
    }

    @GetMapping("/{placeId}")
    public ResponseEntity getSinglePlace(@PathVariable String placeId){
        return ResponseEntity.ok(placeRepository.findById(placeId));
    }

}
