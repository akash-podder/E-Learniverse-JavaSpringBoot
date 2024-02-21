package com.akash.e_learniverse_spring_boot.service.football_player;

import com.akash.e_learniverse_spring_boot.domain.entity.FootballPlayerEntity;

import java.util.List;

public interface FootballPlayerService {

    FootballPlayerEntity savePlayer(FootballPlayerEntity footballPlayer);

    FootballPlayerEntity getFootballPlayerByName(String playerName);

    List<FootballPlayerEntity> getAllFootballPlayer();

    FootballPlayerEntity getFootballPlayerByEmail(String email);
}
