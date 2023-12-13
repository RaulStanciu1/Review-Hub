package com.reviewhub.controller;

import com.reviewhub.dto.ProjectDto;
import com.reviewhub.dto.TeamDto;
import com.reviewhub.entities.Project;
import com.reviewhub.entities.Team;
import com.reviewhub.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/team")
public class TeamController {

    TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    @PostMapping("/create")
    public String createTeam(@RequestBody TeamDto team)
    {
        if (!teamService.checkTeam(team)) {
            return "Team already exists";
        }
        return teamService.createTeam(team);
    }

    @PutMapping("/addUser")
    public String addUser(@RequestBody TeamDto user)
    {
        return teamService.addUser(user);
    }

    @PutMapping("/addProject")
    public String addProject(@RequestBody ProjectDto project)
    {
        return teamService.addProject(project);
    }


}
