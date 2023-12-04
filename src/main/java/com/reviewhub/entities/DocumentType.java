package com.reviewhub.entities;

public enum DocumentType {
    Python,
    Java,
    Text;
    public static DocumentType parseDocType(String type){
        return switch(type){
            case ".java" -> Java;
            case ".py" -> Python;
            default -> Text;
        };
    }
}
