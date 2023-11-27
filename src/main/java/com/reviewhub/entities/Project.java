package com.reviewhub.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
@Document(collection = "projects")
public class Project extends Directory{
    @Id
    private String id;
    public Project(String name) {
        super(name);
    }


}
