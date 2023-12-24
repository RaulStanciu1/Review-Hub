package com.reviewhub.services;

import com.reviewhub.dto.TeamDto;
import com.reviewhub.entities.Project;
import com.reviewhub.entities.Request;
import com.reviewhub.entities.Team;
import com.reviewhub.entities.User;
import com.reviewhub.respository.ProjectRepository;
import com.reviewhub.respository.RequestRepositoty;
import com.reviewhub.respository.TeamRepository;
import com.reviewhub.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeamService {

    TeamRepository teamRepository;
    UserRepository userRepository;
    ProjectRepository projectRepository;
    RequestRepositoty requestRepository;
    @Autowired
    public TeamService(TeamRepository teamRepository, UserRepository userRepository, ProjectRepository projectRepository, RequestRepositoty requestRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.requestRepository = requestRepository;
    }

    public String createTeam(TeamDto team) {
        Team newTeam = new Team(team.getName());
        User user = userRepository.findByName(team.getUser());
        newTeam.addUser(user);
        teamRepository.save(newTeam);
        return "Team created";
        }

    public boolean checkTeam(TeamDto team) {
        Team newTeam = teamRepository.findByName(team.getName());
        return newTeam == null;
    }

    public String addUser(TeamDto user) {
        User newUser = userRepository.findByName(user.getName());
        if (newUser == null) {
            return "User does not exist";
        }
        Team team = teamRepository.findByName(user.getName());
        if (team == null) {
            return "Team does not exist";
        }
        team.addUser(newUser);
        teamRepository.save(team);
        return "User added to team";
    }

    public String addProject(String teamName) {
Team team = teamRepository.findByName(teamName);
        if (team == null) {
            return "Team does not exist";
        }
        Project project = new Project(teamName);
        projectRepository.save(project);
        team.addProject(project);
        teamRepository.save(team);
        return "Project added to team";
    }

    public ResponseEntity<?> addProject(String teamName, Project project) {
        Team team = teamRepository.findByName(teamName);
        if (team == null) {
            return ResponseEntity.badRequest().body("Team does not exist");
        }
        team.addProject(project);
        teamRepository.save(team);
        return ResponseEntity.ok("Project added to team");
    }

    public String requestTeamAccess(Request request) {
        Request id = requestRepository.save(request);
        Team team = teamRepository.findByName(request.getTeamName());
        team.addRequest(request);
        teamRepository.save(team);
        return "Request sent";
    }

    public String acceptTeamRequest(String username, String teamName) {
        Team team = teamRepository.findByName(teamName);
        User user = userRepository.findByName(username);
        team.addUser(user);
        teamRepository.save(team);
        requestRepository.deleteByTeamNameAndAndUsername(teamName, username);
        return "User added to team";
    }

    public String rejectTeamRequest(String username, String teamName) {
        requestRepository.deleteByTeamNameAndAndUsername(teamName, username);
        return "Request rejected";
    }

    public ArrayList<Request> getAllRequests(String username) {
        User user = userRepository.findByName(username);
        ArrayList<Team> teams = teamRepository.findByAdminsContaining(user.getId());
        return requestRepository.findAllByTeamNameIn(teams);
    }

    public ResponseEntity<?>addAdmin(String username, String teamName, String authorizatorUsername) {
        User user = userRepository.findByName(username);
        User authorizator = userRepository.findByName(authorizatorUsername);
        if (user == null) {
            return ResponseEntity.badRequest().body("User does not exist");
        }
        Team team = teamRepository.findByName(teamName);
        if (team == null) {
            return ResponseEntity.badRequest().body("Team does not exist");
        }
        if (!team.getAdmins().contains(authorizator.getId())) {
            return ResponseEntity.badRequest().body("User is not admin");
        }
        team.addAdmin(user);
        teamRepository.save(team);
        return ResponseEntity.ok("User added as admin");
    }

    public ResponseEntity<?> removeAdmin(String username, String teamName, String authorizatorUsername) {
        User user = userRepository.findByName(username);
        User authorizator = userRepository.findByName(authorizatorUsername);
        if (user == null) {
            return  ResponseEntity.badRequest().body("User does not exist");
        }
        Team team = teamRepository.findByName(teamName);
        if (team == null) {
            return ResponseEntity.badRequest().body("Team does not exist");
        }
        if (!team.getAdmins().contains(authorizator.getId())) {
            return ResponseEntity.badRequest().body("User is not admin");
        }
        team.getAdmins().remove(user.getId());
        teamRepository.save(team);
        return ResponseEntity.ok("User removed as admin");
    }
}


