package com.example.cricketapi.temp;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {
    private int matchId;
    private int team1Id;
    private int team2Id;

    private Team team1;
    private int team1_score;
    private int team1_balls_played;

    private Team team2;
    private int team2_score;
    private int team2_balls_played;

    private int overs;
    private String winner;

    public Match() {
        this.overs = 0;
        this.team1_balls_played = 0;
        this.team1_score = 0;
        this.team2_balls_played = 0;
        this.team2_score = 0;
        this.winner = null;
    }

    public Match(int team1Id, int team2Id, Team team1, Team team2, int overs) {
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.team1 = team1;
        this.team2 = team2;
        this.overs = overs;
        this.team1_balls_played = 0;
        this.team1_score = 0;
        this.team2_balls_played = 0;
        this.team2_score = 0;
        this.winner = null;
    }

    private int toBalls(int overs) {
        return 6 * overs;
    }

    public void startMatch() {
        System.out.println("in getMatchWinner");
        // half probability
        int toss = (int)(Math.random() * 2);
        System.out.printf("Team %s won the toss and decided to bat\n", (toss == 0 ? team1.getName() : team2.getName()));
        if(toss == 0) {
            // team1 bat first
            playMatch(team1, team2);
        } else {
            playMatch(team2, team1);
        }

        this.team1_score = team1.getTeamScore();
        this.team1_balls_played = team1.getBallsPlayed();

        this.team2_score = team2.getTeamScore();
        this.team2_balls_played = team2.getBallsPlayed();

        this.winner = getMatchWinner();
    }
    private void playMatch(Team t1, Team t2) {
        System.out.println("Batting Team: " + t1.getName());
        t1.firstInning(toBalls(overs));
        t1.teamDisplay();
        System.out.println();

        System.out.println("Batting Team: " + t2.getName());
        t2.secondInning(toBalls(overs), t1.getTeamScore() + 1);
        t2.teamDisplay();
        System.out.println();
    }


    public String getMatchWinner() {
        if(team1.getTeamScore() > team2.getTeamScore()) {
            System.out.printf("%s won!\n", team1.getName());
            return team1.getName();
        } else if(team1.getTeamScore() < team2.getTeamScore()) {
            System.out.printf("%s won!\n", team2.getName());
            return team2.getName();
        } else {
            System.out.println("Draw.");
            return "Draw";
        }
    }
}
