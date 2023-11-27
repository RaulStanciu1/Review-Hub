package com.reviewhub.entities;

import java.sql.Array;
import java.util.ArrayList;

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

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }
}

