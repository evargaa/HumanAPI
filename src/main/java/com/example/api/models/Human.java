package com.example.api.models;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;


@Entity
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotBlank(message="name can not be empty")
    private String name;
    private int age;

    private String homeTown;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    @Override
    public String toString() {
        return "Human{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + age + '\'' +
                ", homeTown='" + homeTown + '\'' +
                '}';
    }
}
