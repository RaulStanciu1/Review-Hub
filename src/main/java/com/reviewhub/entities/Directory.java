package com.reviewhub.entities;


import java.util.ArrayList;




public class Directory extends FileSystemEntity {
    private ArrayList<FileSystemEntity> children;

    public Directory(String name) {
        super(name);
        this.children = new ArrayList<>();
    }

    public Directory() {

    }

    public void setChildren(ArrayList<FileSystemEntity> arr){
        this.children = arr;
    }


    public void addChild(FileSystemEntity child) {
        this.children.add(child);
    }

    public ArrayList<String> getFileSystemEntityNames() {
        ArrayList<String> names = new ArrayList<>();
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


    public void addDirectory(String name) {
        Directory directory = new Directory(name);
        this.children.add(directory);
    }

    public void addDirectory(Directory directory) {
        this.children.add(directory);
    }

    public void addFile(String name, Document content) {
        File file = new File(name);
        file.addVersion(content);
        this.children.add(file);
    }

    protected Object getChildren() {
        return this.children;
    }
}

