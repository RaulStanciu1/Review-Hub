package com.reviewhub.entities;

public enum DocumentType {
    python,
    java,
    text;
    public static DocumentType parseDocType(String type){
        return switch(type){
            case ".java" -> java;
            case ".py" -> python;
            case ".sample" -> text;
            default -> text;
        };
    }
}
