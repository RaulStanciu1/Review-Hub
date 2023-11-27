package com.reviewhub.services;

import com.reviewhub.entities.Directory;
import com.reviewhub.entities.File;
import com.reviewhub.entities.Project;
import com.reviewhub.respository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createSampleProject() {
        // Create a sample project with files and directories
        Project project = new Project("Sample Project");

        File file1 = new File("File 1");
        file1.addVersion("Content of File 1, Version 1");
        file1.addVersion("Content of File 1, Version 2");

        File file2 = new File("File 2");
        file2.addVersion("Content of File 2, Version 1");

        Directory directory1 = new Directory("Directory 1");
        directory1.addChild(file2);

        project.addChild(file1);
        project.addChild(directory1);

        // Save the project to MongoDB
        return projectRepository.save(project);
    }

    public Project getProjectById(String projectId) {
        // Retrieve a project by ID from MongoDB
        return projectRepository.findById(projectId).orElse(null);
    }
}
