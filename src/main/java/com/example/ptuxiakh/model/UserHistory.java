package com.example.ptuxiakh.model;

import org.springframework.data.annotation.Id;

public class UserHistory {

    @Id
    private String id;

    private String userId;

    //this should describe the event
    private String event;


}
