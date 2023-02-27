package com.example.cricketapi.temp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Player {
    private int playerId;
    private String name;
    private int age;
    private int totalRuns;
    private int ballsPlayed;
    private String type;
    private int teamId;

    //    constructor
    public Player(int playerId, String name, int age, String type, int teamId) {
        this.playerId = playerId;
        this.name = name;
        this.age = age;
        this.totalRuns = 0;
        this.ballsPlayed = 0;
        this.type = type;
        this.teamId = teamId;
    }

    public void batsmanScore() {
        //        using printf function for better format
        System.out.printf("%15s (%s) : %3d (%d) \n ", getName(), getType(), getTotalRuns(), getBallsPlayed());
    }

    //    implemented in batsman and bowler
    public boolean isOut() {
        int chances = type.equals("Batsman") ? 20 : 10;
        return (int)(Math.random()*chances)==0;
    }

    private int scoreRun() {
        //        using random function
        return (int)(Math.random()*7);
    }

    //    bat a bowl
    public int playTheBall() {
        ballsPlayed += 1;
        if(isOut())
            return -1;
        // using scoreRun method
        int run = scoreRun();
        totalRuns += run;
        return run;
    }
}
