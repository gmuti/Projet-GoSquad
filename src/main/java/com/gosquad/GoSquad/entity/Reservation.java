package com.gosquad.GoSquad.entity;

import lombok.Data;

@Data
public class Reservation {
    private String id;
    private Long cost;
    private String date;
    private String reservationType;
    private String userId;
}

