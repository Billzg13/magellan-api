package com.example.ptuxiakh.controllers;

import com.example.ptuxiakh.model.PlacePackage.Place;
import com.example.ptuxiakh.model.PlacePackage.PlacesResponse;
import com.example.ptuxiakh.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

        //System.out.println("this is the results: "+response.getResults());
        int counter = 3;
        for (int i = 0; i < counter; i++) {

            if (response.getNext_page_token() != null || !response.getNext_page_token().isEmpty()) {
                response = restTemplate.getForObject("https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyC9cmX0aKLDwUzDHRwsaSkpsvhoOgQvfOA&pagetoken=" + response.getNext_page_token(),
                        PlacesResponse.class);
                placeRepository.saveAll(response.getResults());
            }
            response.setResults(new ArrayList<>());
        }


        return null;
    }

}
