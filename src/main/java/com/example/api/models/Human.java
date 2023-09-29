package com.example.api.models;

import jakarta.persistence.*;

@Entity
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String username;
    private String password;


}
