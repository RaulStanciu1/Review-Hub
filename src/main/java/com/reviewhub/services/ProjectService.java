package com.reviewhub.services;

import com.reviewhub.entities.Directory;
import com.reviewhub.entities.File;
import com.reviewhub.entities.Project;
import com.reviewhub.respository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project getProjectById(String projectId) {
        // Retrieve a project by ID from MongoDB
        return projectRepository.findById(projectId).orElse(null);
    }

    public String createProject(String projectName, MultipartFile file) {
        Project project = new Project(projectName);
        return 1 + "";
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
