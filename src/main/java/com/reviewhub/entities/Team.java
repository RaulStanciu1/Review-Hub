package com.reviewhub.entities;

import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Project> projects;

    public Team(String name) {
        this.name = name;
        this.users = new ArrayList<User>();
        this.projects = new ArrayList<Project>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public ArrayList<String> getUserNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (User user : this.users) {
            names.add(user.getName());
        }
        return names;
    }

    public ArrayList<String> getProjectNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Project project : this.projects) {
            names.add(project.getName());
        }
        return names;
    }


}
