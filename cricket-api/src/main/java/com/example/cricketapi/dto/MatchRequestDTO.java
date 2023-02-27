package com.example.cricketapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatchRequestDTO {
    int teamId1;
    int teamId2;
    int overs;
}
