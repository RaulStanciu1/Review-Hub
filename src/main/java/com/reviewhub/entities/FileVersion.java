package com.reviewhub.entities;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileVersion {

    private int version;
    @Getter
    private Document document;


    public void addComment(int lineNum, String comment, String commentType) {
        this.document.addComment(lineNum, comment, commentType);
    }

    public String toString(int indentationLevel) {
        StringBuilder result = new StringBuilder();
        String indentation = "  ".repeat(indentationLevel); // Two spaces for each level

        result.append(indentation).append("Version: ").append(this.version).append("\n");
        result.append(this.document.toString(indentationLevel + 1)); // Assuming document has a toString(int) method

        return result.toString();
    }

}