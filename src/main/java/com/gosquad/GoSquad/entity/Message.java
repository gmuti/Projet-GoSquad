package com.gosquad.GoSquad.entity;

import lombok.Data;

@Data
public class Message {
    private String id;
    private String content;
    private String date;
    private String groupeId;
    private String userId;
}
