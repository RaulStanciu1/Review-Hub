package com.reviewhub.services;

import com.reviewhub.entities.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Linter {
    String extension;

    public Linter(String extension) {
        this.extension = extension;
    }

    public static void formatJavaFile(String filePath, List<String> options) {
        try {
            // Construct the command
            String gjfVersion = "1.18.1"; // Replace with the actual GJF version
            String command = String.format("java -jar src/main/resources/Linters/google-java-format-%s-all-deps.jar %s %s",
                    gjfVersion, String.join(" ", options), filePath);

            // Run the command using ProcessBuilder
            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
            Process process = processBuilder.start();

            // Wait for the process to finish
            int exitCode = process.waitFor();

            // Check the exit code
            if (exitCode == 0) {
                System.out.println("File formatted successfully.");
            } else {
                System.err.println("Error formatting file. Exit code: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAllFiles(java.io.File directory) {
        ArrayList<String> files = new ArrayList<>();
        for (java.io.File file : directory.listFiles()) {
            if (file.isDirectory()) {
                files.addAll(getAllFiles(file));
            } else {
                files.add(file.getAbsolutePath());
            }
        }
        return files;
    }

    public void lintJavaFolder(String path) {
        ArrayList<String> files = getAllFiles(new java.io.File(path));
        for (String file : files) {
            if(file.endsWith(".java"))
                formatJavaFile(file, new ArrayList<String>());
        }
    }
}
