package com.reviewhub.entities;

class FileVersion {
    private int version;
    private String content;

    public FileVersion(int version, String content) {
        this.version = version;
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public int getVersion() {
        return this.version;
    }


}