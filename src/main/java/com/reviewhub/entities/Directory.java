package com.reviewhub.entities;


import java.util.ArrayList;




public class Directory extends FileSystemEntity {
    private ArrayList<FileSystemEntity> children;

    public Directory(String name) {
        super(name);
        this.children = new ArrayList<FileSystemEntity>();
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

    public ArrayList<FileSystemEntity> getChildren() {
        return this.children;
    }

    public FileSystemEntity getChildByName(String s) {
        for (FileSystemEntity child : this.children) {
            if (child.getName().equals(s)) {
                return child;
            }
        }
        return null;
    }

    public String toString(int indentationLevel) {
        StringBuilder result = new StringBuilder();
        String indentation = "  ".repeat(indentationLevel); // Two spaces for each level

        result.append(indentation).append(this.getName()).append(":").append("\n");
        for (File child : this.getFiles()) {
            result.append(child.toString(indentationLevel + 1)).append("\n");
        }
        for (Directory child : this.getDirectories()) {
            result.append(child.toString(indentationLevel + 1)).append("\n");
        }
        return result.toString();
    }


    private ArrayList<Directory> getDirectories() {
        ArrayList<Directory> directories = new ArrayList<>();
        for (FileSystemEntity child : this.children) {
            if (child instanceof Directory) {
                directories.add((Directory) child);
            }
        }
        return directories;
    }

    private ArrayList<File> getFiles() {
        ArrayList<File> files = new ArrayList<>();
        for (FileSystemEntity child : this.children) {
            if (child instanceof File) {
                files.add((File) child);
            }
        }
        return files;
    }
}

