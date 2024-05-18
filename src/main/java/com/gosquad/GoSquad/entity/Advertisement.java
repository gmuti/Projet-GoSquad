package com.gosquad.GoSquad.entity;

import lombok.Data;


@Data
public class Advertisement {

    private String id;
    private String description;
    private String image;
    private String link;
    private Double price;
    private String source;
    private String title;

    // Getters and setters
}
