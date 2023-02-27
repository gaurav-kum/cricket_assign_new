package com.example.cricketapi.controller;

import com.example.cricketapi.dto.MatchRequestDTO;
import com.example.cricketapi.dto.MatchResultDTO;
import com.example.cricketapi.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MatchController {

    @Autowired
    MatchService matchService;

    @GetMapping("/match")
    public List<MatchResultDTO> getAllMatch() {
        return matchService.getAllMatch();
    }

    @GetMapping("/match/{matchId}")
    public MatchResultDTO getMatch(@PathVariable int matchId) {
        return matchService.getMatch(matchId);
    }

    @PostMapping("/playMatch")
    public MatchResultDTO playMatch(@RequestBody MatchRequestDTO matchRequestDTO) {
        return matchService.playMatch(matchRequestDTO);
    }
}
