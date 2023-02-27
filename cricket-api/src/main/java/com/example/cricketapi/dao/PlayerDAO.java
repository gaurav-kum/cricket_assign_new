package com.example.cricketapi.dao;

import com.example.cricketapi.temp.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PlayerDAO implements DAO<Player> {

    private static final Logger log = LoggerFactory.getLogger(PlayerDAO.class);
    private JdbcTemplate jdbcTemplate;

    public PlayerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Maps a row in the database to a Course
     */
    RowMapper<Player> rowMapper = (rs, rowNum) -> {
        Player player = new Player();
        player.setPlayerId(rs.getInt("playerId"));
        player.setName(rs.getString("name"));
        player.setAge(rs.getInt("age"));
        player.setType(rs.getString("type"));
        player.setTeamId(rs.getInt("teamId"));
        return player;
    };

    @Override
    public List<Player> list() {
        String sql = "SELECT playerId, name, age, type, teamId from player";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // CRUD (Create, Read, Update, Delete)

    @Override
    public void create(Player player) {
        String sql = "insert into player(name, age, type, teamId) values(?,?,?,?)";
        int insert = jdbcTemplate.update(sql, player.getName(), player.getAge(), player.getType(), player.getTeamId());
        if(insert == 1) {
            log.info("New Player Created: " + player.getName());
        }
    }

    //
    @Override
    public Optional<Player> get(int id) {
        String sql = "SELECT playerId, name, age, type, teamId from player where playerId = ?";
        //
        Player player = null;

        try {
            //
            player = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        }catch (DataAccessException ex) {
            log.info("Player not found: " + id);
        }
        //
        return Optional.ofNullable(player);
    }

    @Override
    public void update(Player player, int id) {
        String sql = "update player set name = ?, age = ? , type = ? , teamId = ? where playerId = ?";
        int update = jdbcTemplate.update(sql,player.getName(), player.getAge(), player.getType(), player.getTeamId(),id);
        if(update == 1) {
            log.info("Player Updated: " + player.getName());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from player where playerId = ?";
        int delete = jdbcTemplate.update(sql,id);
        if(delete == 1) {
            log.info("Player Deleted: " + id);
        }
    }

    public List<Player> getByTeamId(int teamId) {
        String sql = "SELECT playerId, name, age, type, teamId from player where teamId = " + teamId;
        return jdbcTemplate.query(sql, rowMapper);
    }
}
