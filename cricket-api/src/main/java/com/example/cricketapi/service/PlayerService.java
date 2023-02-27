package com.example.cricketapi.service;

import com.example.cricketapi.dao.PlayerDAO;
import com.example.cricketapi.temp.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    PlayerDAO playerDAO;

    public List<Player> getAllPlayerFromDb() {
        return playerDAO.list();
    }
    // optional
    public Player getPlayerFromDb(int playerId) {
        return playerDAO.get(playerId).orElse(null);
    }

    public void savePlayerToDb(Player player) {
        playerDAO.create(player);
    }

    public List<Player> getByTeamId(int teamId) {
        return playerDAO.getByTeamId(teamId);
    }
}
