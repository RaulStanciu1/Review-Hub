package com.reviewhub.entities;


import java.util.ArrayList;

public class File extends FileSystemEntity {
    private ArrayList<FileVersion> versions;
    private int currentVersion = 1;

    public File(String name) {
        super(name);
        this.versions = new ArrayList<FileVersion>();
    }

    public void addVersion(Document document) {
        this.versions.add(new FileVersion(this.currentVersion, document));
        this.currentVersion++;
    }

    public FileVersion getVersion(int version) {
        return this.versions.get(version);
    }

    public FileVersion getCurrentVersion() {
        return this.versions.get(this.currentVersion - 1);
    }

}


