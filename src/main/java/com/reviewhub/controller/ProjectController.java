package com.reviewhub.controller;

import com.reviewhub.entities.Project;
import com.reviewhub.respository.ProjectRepository;
import com.reviewhub.respository.UnzipFolder;
import com.reviewhub.services.Linter;
import com.reviewhub.services.ProjectService;
import jakarta.servlet.annotation.MultipartConfig;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Controller class for managing project-related operations.
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final ProjectService projectService;
    UnzipFolder unzipFolder;

    /**
     * Constructor for ProjectController.
     *
     * @param unzipFolder The UnzipFolder service to be injected.
     * @param projectRepository The ProjectRepository to be injected.
     * @param projectService The ProjectService to be injected.
     */
    public ProjectController(UnzipFolder unzipFolder, ProjectRepository projectRepository, ProjectService projectService) {
        this.unzipFolder = unzipFolder;
        this.projectRepository = projectRepository;
        this.projectService = projectService;
    }

    /**
     * Create a new project.
     *
     * @param name The name of the project.
     * @param file The file representing the project.
     * @return The created Project object.
     * @throws IOException If an I/O exception occurs.
     */
    @PostMapping(value = "/createProject")
    public Project createProject(@RequestParam("Name") String name, @RequestParam("File") MultipartFile file) throws IOException {
        return projectService.createProject(name, file);
    }

    /**
     * Delete a project by name.
     *
     * @param name The name of the project to be deleted.
     * @return A message indicating the result of the deletion.
     */
    @DeleteMapping(value = "/deleteProject")
    public String deleteProject(@RequestParam String name) {
        try {
            projectRepository.deleteByName(name);
            return "Success";
        } catch (Exception e) {
            return "Failure";
        }
    }

    /**
     * Add a comment to a project.
     *
     * @param ProjectName The name of the project.
     * @param filePath The path of the file in which the comment is added.
     * @param lineNum The line number in the file.
     * @param comment The comment to be added.
     * @param commentType The type of comment.
     * @param version The version of the project.
     * @return A message indicating the result of adding the comment.
     */
    @PutMapping(value = "/addComment")
    public String addComment(@RequestParam("ProjectName") String ProjectName, @RequestParam("filePath") String filePath, @RequestParam("lineNum") int lineNum, @RequestParam("Comment") String comment, @RequestParam("CommentType") String commentType, @RequestParam("version") int version) {
        projectService.addComment(ProjectName, filePath, lineNum, comment, commentType, version);
        return "Success";
    }

    /**
     * Create an alternative function in a project.
     *
     * @param ProjectName The name of the project.
     * @param filePath The path of the file in which the alternative function is created.
     * @param funcStart The starting line of the function to be rewriten.
     * @param funcEnd The ending line of the function to be rewriten.
     * @param version The version of the project.
     * @return A message indicating the result of creating the alternative function.
     */
    @PutMapping(value = "/alternativeFunction")
    public String alternative(@RequestParam("ProjectName") String ProjectName, @RequestParam("filePath") String filePath, @RequestParam("funcStart") int funcStart,@RequestParam("funcEnd") int funcEnd, @RequestParam("version") int version) {
        projectService.createAlternateFunc(ProjectName, filePath, funcStart,funcEnd, version);
        return "Success";
    }

    /**
     * Update a project by uploading a new version of the whole project.
     *
     * @param ProjectName The name of the project to be updated.
     * @param file The file representing the updated project.
     * @return A message indicating the result of the update.
     * @throws IOException If an I/O exception occurs.
     */
    @PutMapping(value = "/updateProject")
    public String updateProject(@RequestParam("ProjectName") String ProjectName, @RequestParam("file") MultipartFile file) throws IOException {
        projectService.updateProject(ProjectName, file);
        return "Success";
    }

    /**
     * Update a file in a project.
     *
     * @param ProjectName The name of the project.
     * @param filePath The path of the file to be updated.
     * @param file The file representing the updated content.
     * @return A message indicating the result of the update.
     * @throws IOException If an I/O exception occurs.
     */
    @PutMapping(value = "/updateFile")
    public String updateFile(@RequestParam("ProjectName") String ProjectName, @RequestParam("filePath") String filePath, @RequestParam("file") MultipartFile file) throws IOException {
        projectService.updateFile(ProjectName, filePath, file);
        return "Success";
    }

    /**
     * Create a new file in a project.
     *
     * @param ProjectName The name of the project.
     * @param filePath The path of the file to be created.
     * @param file The file representing the content of the new file.
     * @return A message indicating the result of the creation.
     * @throws IOException If an I/O exception occurs.
     */
    @PostMapping(value = "/createFile")
    public String createFile(@RequestParam("ProjectName") String ProjectName, @RequestParam("filePath") String filePath, @RequestParam("file") MultipartFile file) throws IOException {
        projectService.createFile(ProjectName, filePath, file);
        return "Success";
    }

    /**
     * Delete a file from a project.
     *
     * @param ProjectName The name of the project.
     * @param filePath The path of the file to be deleted.
     * @return A message indicating the result of the deletion.
     * @throws IOException If an I/O exception occurs.
     */
    @DeleteMapping(value = "/deleteFile")
    public String deleteFile(@RequestParam("ProjectName") String ProjectName, @RequestParam("filePath") String filePath) throws IOException {
        projectService.deleteFile(ProjectName, filePath);
        return "Success";
    }

    /**
     * Create a new folder in a project.
     *
     * @param ProjectName The name of the project.
     * @param filePath The path of the folder to be created.
     * @return A message indicating the result of the creation.
     * @throws IOException If an I/O exception occurs.
     */
    @PostMapping(value = "/createFolder")
    public String createFolder(@RequestParam("ProjectName") String ProjectName, @RequestParam("filePath") String filePath) throws IOException {
        projectService.createFolder(ProjectName, filePath);
        return "Success";
    }

    /**
     * Delete a folder from a project.
     *
     * @param ProjectName The name of the project.
     * @param filePath The path of the folder to be deleted.
     * @return A message indicating the result of the deletion.
     * @throws IOException If an I/O exception occurs.
     */
    @DeleteMapping(value = "/deleteFolder")
    public String deleteFolder(@RequestParam("ProjectName") String ProjectName, @RequestParam("filePath") String filePath) throws IOException {
        projectService.deleteFolder(ProjectName, filePath);
        return "Success";
    }
}
