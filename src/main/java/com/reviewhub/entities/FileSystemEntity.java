package com.reviewhub.entities;

abstract class FileSystemEntity {
    private String name;

    public FileSystemEntity(String name) {
        this.name = name;
    }

    protected String getName() {
        return this.name;
    }
}