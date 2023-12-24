package com.reviewhub.entities;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileVersion {

    private int version;
    private Document document;


    public void addComment(int lineNum, String comment, String commentType) {
        this.document.addComment(lineNum, comment, commentType);
    }

    public Document getDocument() {
        return document;
    }
}