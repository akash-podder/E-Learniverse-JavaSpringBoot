package com.akash.e_learniverse_spring_boot.service.football_player;

import com.akash.e_learniverse_spring_boot.domain.dto.FootballPlayerDto;

import java.util.List;

public interface FootballPlayerService {

    FootballPlayerDto savePlayer(FootballPlayerDto footballPlayerDto);

    FootballPlayerDto getFootballPlayerByName(String playerName);

    List<FootballPlayerDto> getAllFootballPlayer();

    FootballPlayerDto getFootballPlayerByEmail(String email);

    void deleteFootballPlayerByEmail(String email);
}
