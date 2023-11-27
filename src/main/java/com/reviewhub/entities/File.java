package com.reviewhub.entities;


import java.util.ArrayList;

public class File extends FileSystemEntity {
    private ArrayList<FileVersion> versions;
    private int currentVersion = 1;

    public File(String name) {
        super(name);
        this.versions = new ArrayList<FileVersion>();
    }

    public void addVersion(String content) {
        this.versions.add(new FileVersion(this.currentVersion, content));
        this.currentVersion++;
    }

    public FileVersion getVersion(int version) {
        return this.versions.get(version);
    }

    public FileVersion getCurrentVersion() {
        return this.versions.get(this.currentVersion - 1);
    }

}


