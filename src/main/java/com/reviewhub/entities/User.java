package com.reviewhub.entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
@Data
public class User {
    @Id
    private ObjectId id;
    private String name;
    private String password;
    private String type;

    private ArrayList<ObjectId> Teams;

    private ArrayList<ObjectId> privateProjects;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.type = "free";
    }

    public void addProject(Project newProject) {
        this.privateProjects.add(newProject.getId());
    }
}

