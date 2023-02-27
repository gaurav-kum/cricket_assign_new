package com.example.cricketapi.controller;

import com.example.cricketapi.service.PlayerService;
import com.example.cricketapi.temp.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @GetMapping("/player")
    public List<Player> getPlayers() {
        return playerService.getAllPlayerFromDb();
    }

    @GetMapping("/player/{playerId}")
    public Player getPlayer(@PathVariable("playerId") int playerId) {
        Player player = playerService.getPlayerFromDb(playerId);
        return player;
    }

    @PostMapping("/player")
    public void addPlayer(@RequestBody Player player) {
        playerService.savePlayerToDb(player);
    }

    @GetMapping("/playerByTeamId/{teamId}")
    public List<Player> getPlayerByTeamId(@PathVariable("teamId") int teamId) {
        return playerService.getByTeamId(teamId);
    }
}
