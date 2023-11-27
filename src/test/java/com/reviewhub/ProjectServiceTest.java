package com.reviewhub;

import com.reviewhub.entities.Project;
import com.reviewhub.services.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProjectServiceTest {

	@Autowired
	private ProjectService projectService;

	@Test
	public void testCreateAndRetrieveProject() {
		// Create a sample project
		Project createdProject = projectService.createSampleProject();

		// Retrieve the project from MongoDB


		// Perform assertions or logging to verify the correctness of the created and retrieved projects
		// For example:
		System.out.println("Created Project: " + createdProject);
	}
}
