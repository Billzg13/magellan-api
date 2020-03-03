package com.example.ptuxiakh.controllers;

import com.example.ptuxiakh.model.PlacePackage.Place;
import com.example.ptuxiakh.model.PlacePackage.PlacesResponse;
import com.example.ptuxiakh.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
public class SeederController {

    /*
    https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyC9cmX0aKLDwUzDHRwsaSkpsvhoOgQvfOA&location=37.9838096,23.7275388&radius=15000&language=el&types=restaurant


     */

    @Autowired
    PlaceRepository placeRepository;

    @GetMapping("/api/seed")
    public ResponseEntity seedDatabase() {
        RestTemplate restTemplate = new RestTemplate();
        PlacesResponse response = restTemplate.getForObject("https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyC9cmX0aKLDwUzDHRwsaSkpsvhoOgQvfOA&location=37.9838096,23.7275388&radius=15000&language=el&types=cafe",
                PlacesResponse.class);
        placeRepository.saveAll(response.getResults());
        int size = response.getResults().size();

        //System.out.println("this is the results: "+response.getResults());
        while(response.next_page_token != null){
            System.out.println("how many times?");
            response = restTemplate.getForObject("https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyC9cmX0aKLDwUzDHRwsaSkpsvhoOgQvfOA&pagetoken=" + response.getNext_page_token(),
                    PlacesResponse.class);
            size += response.getResults().size();
            placeRepository.saveAll(response.getResults());
        }


        System.out.println("size should be: "+size);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
