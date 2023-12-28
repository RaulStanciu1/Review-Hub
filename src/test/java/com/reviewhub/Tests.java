package com.reviewhub;

import com.reviewhub.entities.*;
import com.reviewhub.respository.ProjectRepository;
import com.reviewhub.services.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class Tests {

    ProjectRepository projectRepository;
    ProjectService projectService;
    @Autowired
    Tests(ProjectRepository projectRepository, ProjectService projectService){
        this.projectRepository = projectRepository;
        this.projectService = projectService;
    }

    @Test
    public void mergeTest() {
        Project project1 = new Project();
        project1.setName("ReviewHub");
        Project project2 = new Project();
        project2.setName("ReviewHub");
        File p1 = new File("P1", "content1", "java");
        File p2 = new File("P2", "content2", "java");
        File p22 = new File("P2", "content22", "java");
        File p3 = new File("P3", "content3", "java");
        File p5 = new File("P5", "content5", "java");
        File p6 = new File("P6", "content6", "java");
        File p7 = new File("P7", "content7", "java");

        Directory f1 = new Directory("f1");
        Directory f2 = new Directory("f2");
        Directory f3 = new Directory("f3");

        project1.addChild(p1);
        project1.addChild(p5);
        f1.addChild(p2);
        f1.addChild(p6);
        f2.addChild(p3);
        project1.addChild(f1);
        project1.addChild(f2);


        File a1 = new File("P1", "content1", "java");
        File a2 = new File("P2", "content2", "java");
        File a22 = new File("P2", "content22", "java");
        File a3 = new File("P3", "content3", "java");
        File a4 = new File("P4", "content4", "java");
        File a5 = new File("P5", "content5", "java");
        File a6 = new File("P6", "content6", "java");
        File a7 = new File("P7", "content7", "java");

        Directory b1 = new Directory("f1");
        Directory b2 = new Directory("f2");
        Directory b3 = new Directory("f3");

        project2.addChild(a1);
        project2.addChild(a7);
        b1.addChild(a6);
        b1.addChild(a22);
        b3.addChild(a4);
        project2.addChild(b1);
        project2.addChild(b3);


        Project project3 = projectService.merge(project1, project2);

        //get the file names in project 3
        ArrayList<String> childNames = new ArrayList<String>();
        for (FileSystemEntity child : project3.getFiles()) {
            childNames.add(child.getName());
        }

        //get the folder names in project 3
        ArrayList<String> folderNames = new ArrayList<String>();
        for (FileSystemEntity child : project3.getDirectories()) {
            if (child instanceof Directory) {
                folderNames.add(child.getName());
            }
        }

        ArrayList<String> expectedChildNames = new ArrayList<String>();
        expectedChildNames.add("P1");
        expectedChildNames.add("P7");

        ArrayList<String> expectedFolderNames = new ArrayList<String>();
        expectedFolderNames.add("f1");
        expectedFolderNames.add("f3");

        assert(childNames.equals(expectedChildNames));
        assert(folderNames.equals(expectedFolderNames));








    }


    //if new folder in second but not in first, add it in final
    //if folder in old but not in new, remove
    //if folder in both, go down recursively check


    //if new file in second, add it in final
    //if file in old but not in new, remove
    //if file in both, check if content is same, if not, add new version, if same, do nothing
}
