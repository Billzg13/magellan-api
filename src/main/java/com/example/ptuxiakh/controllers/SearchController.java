package com.example.ptuxiakh.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/places")
public class SearchController {
    Logger logger = LoggerFactory.getLogger(SearchController.class);

}
