package com.reviewhub.controller;

import com.reviewhub.dto.ProjectDto;
import com.reviewhub.dto.TeamDto;
import com.reviewhub.entities.Project;
import com.reviewhub.entities.Request;
import com.reviewhub.entities.Team;
import com.reviewhub.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller class for managing team-related operations.
 */
@RestController
@RequestMapping(path="/team")
public class TeamController {

    TeamService teamService;
    ProjectController projectController;

    /**
     * Constructor for TeamController.
     *
     * @param teamService The TeamService to be injected.
     * @param projectController The ProjectController to be injected.
     */
    @Autowired
    public TeamController(TeamService teamService, ProjectController projectController) {
        this.teamService = teamService;
        this.projectController = projectController;
    }

    /**
     * Create a new team.
     *
     * @param team The TeamDto containing team information.
     * @return A success message or an indication that the team already exists.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createTeam(@RequestBody TeamDto team) {
        if (!teamService.checkTeam(team)) {
            return ResponseEntity.badRequest().body("Team already exists");
        }
        return ResponseEntity.ok(teamService.createTeam(team));
    }

    /**
     * Request access to join a team.
     *
     * @param request The Request object containing request details.
     * @return A message indicating the result of the request.
     */
    @PutMapping("/requestTeamAccess")
    public ResponseEntity<?> requestTeamAccess(@RequestBody Request request) {
        return ResponseEntity.ok(teamService.requestTeamAccess(request));
    }

    /**
     * Accept a team access request.
     *
     * @param username The username of the user accepting the request.
     * @param teamName The name of the team for which access is requested.
     * @return A message indicating the result of accepting the request.
     */
    @PostMapping("/acceptTeamRequest")
    public ResponseEntity<?> acceptTeamRequest(@RequestParam("username") String username, @RequestParam("teamName") String teamName, @RequestParam("projectName") String projectName) {
        return ResponseEntity.ok(teamService.acceptTeamRequest(username, teamName));
    }

    /**
     * Reject a team access request.
     *
     * @param username The username of the user rejecting the request.
     * @param teamName The name of the team for which access is requested.
     * @return A message indicating the result of rejecting the request.
     */
    @PostMapping("/rejectTeamRequest")
    public ResponseEntity<?> rejectTeamRequest(@RequestParam("username") String username, @RequestParam("teamName") String teamName) {
        return ResponseEntity.ok(teamService.rejectTeamRequest(username, teamName));
    }

    /**
     * Get all access requests for a user.
     *
     * @param username The username of the user for whom requests are retrieved.
     * @return List of access requests for the user.
     */
    @GetMapping("/getAllRequests")
    public ArrayList<Request> getAllRequests(@RequestParam("username") String username) {
        return teamService.getAllRequests(username);
    }

    /**
     * Add a project to a team.
     *
     * @param teamName The name of the team to which the project is added.
     * @param projectName The name of the project to be added.
     * @param file The file representing the project.
     * @return A message indicating the result of adding the project.
     * @throws IOException If an I/O exception occurs.
     */
    @PutMapping("/addProject")
    public ResponseEntity<?> addProject(@RequestParam("teamName") String teamName, @RequestParam("projectName") String projectName, @RequestParam("File") MultipartFile file) throws IOException {
        Project project = projectController.createProject(projectName, file);
        return teamService.addProject(teamName, project);
    }

    /**
     * Add an admin to a team.
     *
     * @param username The username of the user being added as an admin.
     * @param teamName The name of the team to which the admin is added.
     * @param authorizatorUsername The username of the user adding an admin. Has to be an admin.
     * @return A message indicating the result of adding the admin.
     */
    @PostMapping("/addAdmin")
    public ResponseEntity<?> addAdmin(@RequestParam("username") String username, @RequestParam("teamName") String teamName, @RequestParam("AuthorizatorUsername") String authorizatorUsername) {
        return teamService.addAdmin(username, teamName, authorizatorUsername);
    }

    /**
     * Remove an admin from a team.
     *
     * @param username The username of the admin being removed.
     * @param teamName The name of the team from which the admin is removed.
     * @param authorizatorUsername The username of the user deleting an admin. Has to be an admin.
     * @return A message indicating the result of removing the admin.
     */
    @PostMapping("/removeAdmin")
    public ResponseEntity<?> removeAdmin(@RequestParam("username") String username, @RequestParam("teamName") String teamName, @RequestParam("AuthorizatorUsername") String authorizatorUsername) {
        return teamService.removeAdmin(username, teamName, authorizatorUsername);
    }

}
