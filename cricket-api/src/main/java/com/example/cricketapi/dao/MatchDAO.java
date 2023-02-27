package com.example.cricketapi.dao;

import com.example.cricketapi.temp.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MatchDAO implements DAO<Match> {
    private static final Logger log = LoggerFactory.getLogger(PlayerDAO.class);
    private JdbcTemplate jdbcTemplate;

    public MatchDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Maps a row in the database to a Course
     */
    RowMapper<Match> rowMapper = (rs, rowNum) -> {
        Match match = new Match();
        match.setMatchId(rs.getInt("matchId"));
        match.setTeam1Id(rs.getInt("team1Id"));
        match.setTeam2Id(rs.getInt("team2Id"));
        match.setTeam1_score(rs.getInt("team1_score"));
        match.setTeam1_balls_played(rs.getInt("team1_balls_played"));
        match.setTeam2_score(rs.getInt("team2_score"));
        match.setTeam2_balls_played(rs.getInt("team2_balls_played"));
        match.setOvers(rs.getInt("overs"));
        match.setWinner(rs.getString("winner"));
        return match;
    };

    @Override
    public List<Match> list() {
        String sql = "SELECT matchId, team1Id, team1_score, team1_balls_played, team2Id, team2_score, team2_balls_played, overs, winner from matches";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // CRUD (Create, Read, Update, Delete)

    @Override
    public void create(Match match) {
        String sql = "insert into matches(team1Id, team1_score, team1_balls_played, team2Id, team2_score, " +
                     "team2_balls_played, overs, winner) values(?, ?, ?, ?, ?, ?, ?, ?)";
        int insert = jdbcTemplate.update(sql, match.getTeam1Id(), match.getTeam1_score(),
                match.getTeam1_balls_played(), match.getTeam2Id(), match.getTeam2_score(),
                match.getTeam2_balls_played(), match.getOvers(), match.getMatchWinner());
        if(insert == 1) {
            log.info("New Match Created: " + match.getMatchId());
        }
    }

    @Override
    public Optional<Match> get(int id) {
        String sql = "SELECT matchId, team1Id, team1_score, team1_balls_played, team2Id, team2_score, " +
                     "team2_balls_played, overs, winner from matches where matchId = ?";
        Match match = null;
        try {
            match = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        }catch (DataAccessException ex) {
            log.info("Match not found: " + id);
        }
        return Optional.ofNullable(match);
    }

    @Override
    public void update(Match match, int id) {
        String sql = "update matches set team1Id = ?, team1_score = ?, team1_balls_played = ?, team2Id = ?, " +
                     "team2_score = ?, team2_balls_played = ?, overs = ?, winner = ? where matchId = ?";
        int update = jdbcTemplate.update(sql,match.getMatchId(), match.getTeam1Id(), match.getTeam1_score(),
                match.getTeam1_balls_played(), match.getTeam2Id(), match.getTeam2_score(),
                match.getTeam2_balls_played(), match.getOvers(), match.getMatchWinner(), match.getMatchId());
        if(update == 1) {
            log.info("Match Updated: " + match.getMatchId());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from matches where matchId = ?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            log.info("Match Deleted: " + id);
        }
    }
}
