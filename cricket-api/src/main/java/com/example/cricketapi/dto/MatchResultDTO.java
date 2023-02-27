package com.example.cricketapi.dto;

import com.example.cricketapi.temp.Match;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchResultDTO {
    private int matchId;

    private String team1;
    private int team1_score;
    private int team1_balls_played;

    private String team2;
    private int team2_score;
    private int team2_balls_played;

    private int overs;
    private String winner;

    public MatchResultDTO(Match match) {
        this.matchId = match.getMatchId();
        this.team1 = match.getTeam1().getName();
        this.team1_score = match.getTeam1_score();
        this.team1_balls_played = match.getTeam1_balls_played();
        this.team2 = match.getTeam2().getName();
        this.team2_score = match.getTeam2_score();
        this.team2_balls_played = match.getTeam2_balls_played();
        this.overs = match.getOvers();
        this.winner = match.getWinner();
    }
}
