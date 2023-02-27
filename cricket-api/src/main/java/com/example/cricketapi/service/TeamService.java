package com.example.cricketapi.service;

import com.example.cricketapi.dao.TeamDAO;
import com.example.cricketapi.temp.Team;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    TeamDAO teamDAO;

    public List<Team> getAllTeams() {
        return teamDAO.list();
    }

    public Team getTeam(int teamId) {
        return teamDAO.get(teamId).orElse(null);
    }

    public void addTeam(Team team) {
        teamDAO.create(team);
    }
}
