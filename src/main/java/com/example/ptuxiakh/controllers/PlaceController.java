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
    public ResponseEntity getAllPlaces(@RequestParam(required = false) String type){
        try{
            if (type == null)
                return ResponseEntity.ok(placeService.getAllPlaces());

            return ResponseEntity.ok(placeService.getAllPlacesByType(type));
        }catch (NullPointerException exc){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
       /* List<Place> places;
        if (type == null){
            places = placeRepository.findAll();
        }else{
            places = placeRepository.findAllByTypes(type);
        }

        return ResponseEntity.ok(places.stream().map( place -> {
            PlaceProjection placeProjection = new PlaceProjection(place);
            return placeProjection;
        }).collect(Collectors.toList())); */
    }

    @GetMapping("/{placeId}")
    public ResponseEntity getSinglePlace(@PathVariable String placeId){
        try {
            return ResponseEntity.ok(placeService.getSinglePlace(placeId));
        }catch (NullPointerException exc){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
       // return ResponseEntity.ok(placeRepository.findById(placeId));
    }

}
