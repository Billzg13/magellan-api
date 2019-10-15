package com.example.ptuxiakh.model.general;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;

public class GoOutPlace {

    @Id
    String id;

    String name;

    //location of the bar
    String location;

    String dateAdded;

    //lowest price of the place
    Double lowPrice;
    //most expensive price of the place
    Double highPrice;

    int population;

    String description;

    String category;

    ArrayList<String> tags;

}
