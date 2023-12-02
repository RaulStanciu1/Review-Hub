package com.reviewhub.entities;

import lombok.Getter;

class FileVersion {
    @Getter
    private int version;
    private Document document;

    public FileVersion(int version, Document document) {
        this.version = version;
        this.document = document;
    }

    public String getDocument() {
        return this.document.getContent();
    }


}