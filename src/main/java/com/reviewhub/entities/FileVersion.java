package com.reviewhub.entities;

import lombok.Data;

@Data
class FileVersion {
    private int version;
    private Document document;

    public FileVersion(int version, Document document) {
        this.version = version;
        this.document = document;
    }


}