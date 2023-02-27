package com.example.cricketapi.service;

import com.example.cricketapi.dao.MatchDAO;
import com.example.cricketapi.dao.PlayerDAO;
import com.example.cricketapi.dao.TeamDAO;
import com.example.cricketapi.dto.MatchRequestDTO;
import com.example.cricketapi.dto.MatchResultDTO;
import com.example.cricketapi.temp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MatchService {
    @Autowired
    TeamDAO teamDAO;

    @Autowired
    PlayerDAO playerDAO;

    @Autowired
    MatchDAO matchDAO;

    public List<MatchResultDTO> getAllMatch() {
        return matchDAO.list()
                       .stream().map(match -> {
                           match.setTeam1(teamDAO.get(match.getTeam1Id()).get());
                           match.setTeam2(teamDAO.get(match.getTeam2Id()).get());
                           return match;
                       })
                       .map(MatchResultDTO::new)
                       .toList();
    }

    public MatchResultDTO getMatch(int matchId) {
        return matchDAO.get(matchId)
                       .map(match -> {
                           match.setTeam1(teamDAO.get(match.getTeam1Id()).get());
                           match.setTeam2(teamDAO.get(match.getTeam2Id()).get());
                           return match;
                       })
                       .map(MatchResultDTO::new)
                       .orElse(null);
    }

    public MatchResultDTO playMatch(@RequestBody MatchRequestDTO matchRequestDTO) {
        Team team1 = teamDAO.get(matchRequestDTO.getTeamId1()).get();
        Team team2 = teamDAO.get(matchRequestDTO.getTeamId2()).get();

        List<Player> players1 = playerDAO.getByTeamId(matchRequestDTO.getTeamId1());
        List<Player> players2 = playerDAO.getByTeamId(matchRequestDTO.getTeamId2());

        team1.setPlayers(players1);
        team2.setPlayers(players2);

        Match match = new Match(team1.getTeamId(), team2.getTeamId(), team1, team2, matchRequestDTO.getOvers());
        match.startMatch();
        matchDAO.create(match);

        return new MatchResultDTO(match);
    }
}
