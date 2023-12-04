package com.reviewhub.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.ArrayList;

@Document(collection = "projects")
public class Project extends Directory{
    @Id
    private String id;
    public Project(String name) {
        super(name);
    }

    //create a conversion from directory to project
    public Project(Directory directory){
        super(directory.getName());
        this.setChildren((ArrayList<FileSystemEntity>) directory.getChildren());
    }



}
