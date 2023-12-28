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
        this.setChildren(new ArrayList<>());
    }
    public Project() {
        super();
        this.setChildren(new ArrayList<>());
    }

    //create a conversion from directory to project
    public Project(Directory directory){
        super(directory.getName());
        this.setChildren((ArrayList<FileSystemEntity>) directory.getChildren());
    }


    @Override
    public String toString(int indentationLevel) {
        StringBuilder result = new StringBuilder();
        String indentation = "  ".repeat(indentationLevel); // Two spaces for each level

        for (File child : this.getFiles()) {
            result.append(indentation).append(child.toString(indentationLevel + 1)).append("\n");
        }
        for (Directory child : this.getDirectories()) {
            result.append(indentation).append(child.toString(indentationLevel + 1)).append("\n");
        }

        return result.toString();
    }


    private ArrayList<File> getFiles() {
        ArrayList<File> files = new ArrayList<>();
        for (FileSystemEntity child : this.getChildren()) {
            if (child instanceof File) {
                files.add((File) child);
            }
        }
        return files;
    }

    private ArrayList<Directory> getDirectories() {
        ArrayList<Directory> directories = new ArrayList<>();
        for (FileSystemEntity child : this.getChildren()) {
            if (child instanceof Directory) {
                directories.add((Directory) child);
            }
        }
        return directories;
    }
}
