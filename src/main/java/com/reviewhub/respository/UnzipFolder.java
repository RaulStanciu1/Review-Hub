package com.reviewhub.respository;

import com.reviewhub.entities.Directory;
import com.reviewhub.entities.Document;
import com.reviewhub.entities.Project;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Repository
public class UnzipFolder {


    public static ProjectRepository projectRepository = null;

    public UnzipFolder(ProjectRepository projectRepository) {
        UnzipFolder.projectRepository = projectRepository;
    }

    public static void unzip(MultipartFile file, String destDirectory) throws IOException {
        File convertedFile = new File(file.getName());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        }
        ZipFile zipFile = new ZipFile(convertedFile);
        FileUtils.forceMkdir(new File(destDirectory));

        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            File entryDestination = new File(destDirectory, entry.getName());

            if (entry.isDirectory()) {
                FileUtils.forceMkdir(entryDestination);
            } else {
                FileUtils.copyInputStreamToFile(zipFile.getInputStream(entry), entryDestination);
            }
        }

        zipFile.close();
    }


    public static Directory loadFileToDb(String path) throws IOException {
        File file = new File(path);
        String name = file.getName();
        String[] split = name.split("\\.");
        name = split[split.length - 1];
        Directory project = new Directory(name);
        for (File f : Objects.requireNonNull(file.listFiles())) {
            if (f.isDirectory()) {
                project.addDirectory(loadFileToDb(f.getAbsolutePath()));
            } else {
                try{
                    List<String> fileContent = readFileContent(f.getPath());
                    Document document = new Document(fileContent, f.getName().split("\\.")[1]);
                    project.addFile(f.getName(), document);
                } catch (Exception e) {
                    continue;
                }
            }
        }
        file.delete();

        return project;
    }


    public static List<String> readFileContent(String filePath) throws IOException {
        Path path = Path.of(filePath);
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }

    public static void deleteDirectory(File file1) throws IOException {
        Files.walk(file1.toPath())
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

}
