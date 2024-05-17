package com.gosquad.GoSquad.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User {
    private String id;
    @Getter
    @Setter
    private String mail;
    private String password;
    private String name;
}
