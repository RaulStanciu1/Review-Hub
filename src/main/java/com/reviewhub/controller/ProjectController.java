package com.reviewhub.controller;

import com.reviewhub.entities.Project;
import com.reviewhub.respository.ProjectRepository;
import com.reviewhub.respository.UnzipFolder;
import com.reviewhub.services.Linter;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@RestController("/project")
public class ProjectController {

    private final ProjectRepository projectRepository;
    UnzipFolder unzipFolder;

    public ProjectController(UnzipFolder unzipFolder, ProjectRepository projectRepository) {
        this.unzipFolder = unzipFolder;
        this.projectRepository = projectRepository;
    }
    @PostMapping(value = "/create")
    public String handleFileUpload(@RequestBody MultipartFile file) throws IOException {
        UnzipFolder.unzip(file,"./uploads");
        File file1 = new File("./uploads");
        String path = String.valueOf(Objects.requireNonNull(file1.listFiles())[0]);
        Project project = new Project(UnzipFolder.loadFileToDb(path));
        Linter linter = new Linter("java");
        linter.lintJavaFolder(path);
        projectRepository.save(project);
        UnzipFolder.deleteDirectory(file1);
        return path;
    }
}

