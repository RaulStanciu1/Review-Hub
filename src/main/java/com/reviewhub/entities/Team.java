package com.reviewhub.entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@Data
public class Team {
    @Id
    private ObjectId id;
    private String name;
    private ArrayList<ObjectId> users;
    private ArrayList<ObjectId> projects;

    public Team(String name) {
        this.name = name;
        this.users = new ArrayList<ObjectId>();
        this.projects = new ArrayList<ObjectId>();
    }

    public void addUser(User user) {
        this.users.add(user.getId());
    }

    public void addProject(Project project) {
        this.projects.add(project.getId());
    }
}
