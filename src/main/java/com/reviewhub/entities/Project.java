package com.reviewhub.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.ArrayList;
@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "projects")
public class Project extends Directory{
    @Id
    private ObjectId id;
    public Project(String name) {
        super(name);
    }
    public Project() {
        super();
    }

    //create a conversion from directory to project
    public Project(Directory directory){
        super(directory.getName());
        this.setChildren((ArrayList<FileSystemEntity>) directory.getChildren());
    }



}
