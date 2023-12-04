package com.reviewhub.entities;

import lombok.Data;

import java.util.ArrayList;
@Data
public class User {
    private String name;
    private String password;
    private String type;

    private ArrayList<String> TeamNames;

    private ArrayList<Project> privateProjects;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.type = "free";
    }
}

