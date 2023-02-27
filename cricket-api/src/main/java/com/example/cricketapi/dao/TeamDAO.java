package com.example.cricketapi.dao;

import com.example.cricketapi.temp.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TeamDAO implements DAO<Team> {
    private static final Logger log = LoggerFactory.getLogger(PlayerDAO.class);
    private JdbcTemplate jdbcTemplate;

    public TeamDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Maps a row in the database to a Course
     */
    RowMapper<Team> rowMapper = (rs, rowNum) -> {
        Team team = new Team();
        team.setTeamId(rs.getInt("teamId"));
        team.setName(rs.getString("name"));
        return team;
    };

    @Override
    public List<Team> list() {
        String sql = "SELECT teamId, name from team";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // CRUD (Create, Read, Update, Delete)

    @Override
    public void create(Team team) {
        String sql = "insert into team(name) values(?)";
        int insert = jdbcTemplate.update(sql, team.getName());
        if(insert == 1) {
            log.info("New Team Created: " + team.getName());
        }
    }

    @Override
    public Optional<Team> get(int id) {
        String sql = "SELECT teamId, name from team where teamId = ?";
        Team team = null;
        try {
            team = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        }catch (DataAccessException ex) {
            log.info("Team not found: " + id);
        }
        return Optional.ofNullable(team);
    }

    @Override
    public void update(Team team, int id) {
        String sql = "update team set name = ? where teamId = ?";
        int update = jdbcTemplate.update(sql,team.getName(),id);
        if(update == 1) {
            log.info("Team Updated: " + team.getName());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from team where teamId = ?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            log.info("Team Deleted: " + id);
        }
    }
}
