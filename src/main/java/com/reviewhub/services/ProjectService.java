package com.reviewhub.services;

import com.reviewhub.entities.*;
import com.reviewhub.respository.ProjectRepository;
import com.reviewhub.respository.UnzipFolder;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ChatService chatService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ChatService chatService) {
        this.projectRepository = projectRepository;
        this.chatService = chatService;
    }

    public Project getProjectById(String projectId) {
        // Retrieve a project by ID from MongoDB
        return projectRepository.findById(projectId).orElse(null);
    }

    public Project createProject(String projectName, MultipartFile file) throws IOException {
        UnzipFolder.unzip(file, "./uploads");
        java.io.File file1 = new java.io.File("./uploads");
        String path = String.valueOf(Objects.requireNonNull(file1.listFiles())[0]);
        Project project = new Project(UnzipFolder.loadFileToDb(path));
        Linter linter = new Linter("java");
        linter.lintJavaFolder(path);
        projectRepository.save(project);
        UnzipFolder.deleteDirectory(file1);
        return project;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public void addComment(String projectName, String filePath, int lineNum, String comment, String commentType, int version) {
        Project project = projectRepository.findByName(projectName);
        FileSystemEntity ptr = project;
        System.out.println(ptr.getName());
        String[] path = filePath.split("/");
        for (int i = 0; i < path.length - 1; i++) {
            ptr = ((Directory) ptr).getChildByName(path[i]);
            System.out.println(ptr.getName());
        }
        ptr = ((Directory) ptr).getChildByName(path[path.length - 1]);
        System.out.println(ptr.getName());
        ((File) ptr).addComment(lineNum, comment, commentType, version);
        projectRepository.save(project);
    }

    public void createAlternateFunc(String projectName, String filePath, int funcStart, int funcEnd, int version) {
        Project project = projectRepository.findByName(projectName);
        FileSystemEntity ptr = project;
        System.out.println(ptr.getName());
        String[] path = filePath.split("/");
        for (int i = 0; i < path.length - 1; i++) {
            ptr = ((Directory) ptr).getChildByName(path[i]);
            System.out.println(ptr.getName());
        }
        ptr = ((Directory) ptr).getChildByName(path[path.length - 1]);
        System.out.println(ptr.getName());
        FileVersion fileVersion = ((File) ptr).getVersion(version);
        String[] lines = fileVersion.getDocument().getContent().split("\n");
        String newLines = "";
        for (int i = funcStart - 1; i < funcEnd - 1; i++) {
            newLines += lines[i] + "\n";
        }
        String replacement = chatService.rewrite(newLines);
        ((File) ptr).addComment(funcStart, replacement, "Alternative", version);
        projectRepository.save(project);
    }
    //TestMe
    public void updateProject(String projectName, MultipartFile file) throws IOException {
        UnzipFolder.unzip(file, "./uploads");
        java.io.File file1 = new java.io.File("./uploads");
        String path = String.valueOf(Objects.requireNonNull(file1.listFiles())[0]);
        Linter linter = new Linter("java");
        linter.lintJavaFolder(path);
        Project projectNew = new Project(UnzipFolder.loadFileToDb(path));
        Project projectOld = projectRepository.findByName(projectName);
        Project finalProject = merge((Directory) projectOld, (Directory) projectNew);
        projectRepository.deleteByName(projectName);
        projectRepository.save(finalProject);
        UnzipFolder.deleteDirectory(file1);
    }
    //TestMe
    private Project merge(Directory directory1, Directory directory2) {
        Project project = new Project();
        project.setName(directory1.getName());

        for (FileSystemEntity fileSystemEntity : directory1.getChildren()) {
            if (fileSystemEntity instanceof Directory && directory2.getChildren().contains(fileSystemEntity)) {
                project.addChild(merge((Directory) fileSystemEntity, (Directory) directory2.getChildByName(fileSystemEntity.getName())));
            } else if (fileSystemEntity instanceof File && !directory2.getChildren().contains(fileSystemEntity)) {
                //do nothing
            }
        }
        for (FileSystemEntity fileSystemEntity : directory2.getChildren()) {
            if (fileSystemEntity instanceof File && !(directory1.getChildren().contains(fileSystemEntity))) {
                project.addChild(fileSystemEntity);
            } else if (fileSystemEntity instanceof File && directory1.getChildren().contains(fileSystemEntity)) {
                File file1 = (File) directory1.getChildByName(fileSystemEntity.getName());
                File file2 = (File) fileSystemEntity;
                if (file1.getCurrentVersion().getDocument().getContent().equals(file2.getCurrentVersion().getDocument().getContent())) {
                    project.addChild(file1);
                } else {
                    File file = new File(file1.getName());
                    file.addVersion(file2.getCurrentVersion().getDocument());
                    project.addChild(file);
                }
            } else if (fileSystemEntity instanceof Directory && !(directory1.getChildren().contains(fileSystemEntity))) {
                project.addChild(fileSystemEntity);
            }
        }

        return project;


    }
    //TestMe
    public void updateFile(String projectName, String filePath, MultipartFile file) throws IOException {
        UnzipFolder.unzip(file, "./uploads");
        java.io.File file1 = new java.io.File("./uploads");
        String path_to_thing = String.valueOf(Objects.requireNonNull(file1.listFiles())[0]);
        Project project = projectRepository.findByName(projectName);
        FileSystemEntity ptr = project;
        String[] path = filePath.split("/");
        for (int i = 0; i <= path.length - 1; i++) {
            ptr = ((Directory) ptr).getChildByName(path[i]);
        }
        ((File) ptr).addVersion((Document) UnzipFolder.readFileContent(path_to_thing));
        projectRepository.save(project);
    }

    //TestMe
    public void createFile(String projectName, String filePath, MultipartFile file) throws IOException {
        UnzipFolder.unzip(file, "./uploads");
        java.io.File file1 = new java.io.File("./uploads");
        String path_to_thing = String.valueOf(Objects.requireNonNull(file1.listFiles())[0]);
        Project project = projectRepository.findByName(projectName);
        FileSystemEntity ptr = project;
        String[] path = filePath.split("/");
        for (int i = 0; i <= path.length -1; i++) {
            ptr = ((Directory) ptr).getChildByName(path[i]);
        }
        ((Directory) ptr).addFile(file.getName(), (Document) UnzipFolder.readFileContent(path_to_thing));
    }
    //TestMe
    public void deleteFile(String projectName, String filePath) {
        Project project = projectRepository.findByName(projectName);
        FileSystemEntity ptr = project;
        String[] path = filePath.split("/");
        for (int i = 0; i <= path.length - 2; i++) {
            ptr = ((Directory) ptr).getChildByName(path[i]);
        }
        ((Directory) ptr).getChildren().remove(((Directory) ptr).getChildByName(path[path.length - 1]));
        projectRepository.save(project);
    }

    //Send the relative path with the new folder included
    //TestMe
    public void createFolder(String projectName, String filePath) {
        Project project = projectRepository.findByName(projectName);
        FileSystemEntity ptr = project;
        String[] path = filePath.split("/");
        for (int i = 0; i <= path.length - 2; i++) {
            ptr = ((Directory) ptr).getChildByName(path[i]);
        }
        ((Directory) ptr).addDirectory(path[path.length - 1]);
        projectRepository.save(project);
    }
    //TestMe
    //Send the relative path with the folder to be deleted included
    public void deleteFolder(String projectName, String filePath) {
        Project project = projectRepository.findByName(projectName);
        FileSystemEntity ptr = project;
        String[] path = filePath.split("/");
        for (int i = 0; i <= path.length - 2; i++) {
            ptr = ((Directory) ptr).getChildByName(path[i]);
        }
        ((Directory) ptr).getChildren().remove(((Directory) ptr).getChildByName(path[path.length - 1]));
        projectRepository.save(project);
    }
}
