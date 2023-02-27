package com.example.cricketapi.controller;

import com.example.cricketapi.service.TeamService;
import com.example.cricketapi.temp.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/teams")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/teams/{teamId}")
    public Team getTeam(@PathVariable int teamId) {
        return teamService.getTeam(teamId);
    }

    @PostMapping("/teams")
    public void addTeam(@RequestBody Team team) {
        teamService.addTeam(team);
    }
}
