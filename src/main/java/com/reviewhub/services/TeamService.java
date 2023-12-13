package com.reviewhub.services;

import com.reviewhub.controller.UserDto;
import com.reviewhub.dto.ProjectDto;
import com.reviewhub.dto.TeamDto;
import com.reviewhub.entities.Project;
import com.reviewhub.entities.Team;
import com.reviewhub.entities.User;
import com.reviewhub.respository.ProjectRepository;
import com.reviewhub.respository.TeamRepository;
import com.reviewhub.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TeamService {

    TeamRepository teamRepository;
    UserRepository userRepository;
    ProjectRepository projectRepository;
    @Autowired
    public TeamService(TeamRepository teamRepository, UserRepository userRepository, ProjectRepository projectRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public String createTeam(TeamDto team) {
        Team newTeam = new Team(team.getName());
        User user = userRepository.findByName(team.getUser());
        newTeam.addUser(user);
        teamRepository.save(newTeam);
        return newTeam.getId().toString();
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

    public String addProject(ProjectDto project) {
        if (Objects.equals(project.getType(), "Team")){
            Team team = teamRepository.findByName(project.getPlace());
            if (team == null) {
                return "Team does not exist";
            }
            Project newProject = projectRepository.findByName(project.getName());
            team.addProject(newProject);
            teamRepository.save(team);
            return "Project added to team";
        }
        else {
            User user = userRepository.findByName(project.getPlace());
            if (user == null) {
                return "User does not exist";
            }
            Project newProject = projectRepository.findByName(project.getName());
            user.addProject(newProject);
            userRepository.save(user);
            return "Project added to user";
        }
    }
}


