package com.reviewhub.entities;

import org.springframework.data.annotation.Id;

import java.sql.Array;
import java.util.ArrayList;





public class Directory extends FileSystemEntity {
    private ArrayList<FileSystemEntity> children;

    public Directory(String name) {
        super(name);
        this.children = new ArrayList<FileSystemEntity>();
    }

    public void addChild(FileSystemEntity child) {
        this.children.add(child);
    }

    public ArrayList<String> getFileSystemEntityNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (FileSystemEntity child : this.children) {
            names.add(child.getName());
        }
        return names;
    }

    public FileSystemEntity getChild(String name) {
        for (FileSystemEntity child : this.children) {
            if (child.getName().equals(name)) {
                return child;
            }
        }
        return null;
    }


}

