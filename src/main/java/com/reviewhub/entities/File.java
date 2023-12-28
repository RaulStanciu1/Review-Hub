package com.reviewhub.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class File extends FileSystemEntity {
    private ArrayList<FileVersion> versions;
    private int currentVersion = 1;

    public File(String name) {
        super(name);
        this.versions = new ArrayList<FileVersion>();
    }

    public File(String name, String content, String type) {
        super(name);
        this.versions = new ArrayList<FileVersion>();
        this.versions.add(new FileVersion(1, new Document(content, type)));
    }

    public void addVersion(Document document) {
        this.versions.add(new FileVersion(this.currentVersion, document));
        this.currentVersion++;
    }

    public FileVersion getVersion(int version) {
        return this.versions.get(version - 1);
    }

    public FileVersion getCurrentVersion() {
        return this.versions.get(this.currentVersion - 1);
    }

    public void addComment(int lineNum, String comment, String commentType, int version) {
        this.getVersion(version).addComment(lineNum, comment, commentType);
    }

    public String toString(int indentationLevel) {
        StringBuilder result = new StringBuilder();
        String indentation = "  ".repeat(indentationLevel); // Two spaces for each level

        result.append(indentation).append(this.getName()).append(":").append("\n");
        return result.toString();
    }


}


