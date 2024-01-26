package com.akash.e_learniverse_spring_boot.service;

import com.akash.e_learniverse_spring_boot.entity.FootballPlayerEntity;

import java.util.List;

public interface FootballPlayerService {

    FootballPlayerEntity savePlayer(FootballPlayerEntity footballPlayer);

    FootballPlayerEntity getFootballPlayerByName(String playerName);

    List<FootballPlayerEntity> getAllFootballPlayer();

    FootballPlayerEntity getFootballPlayerByEmail(String email);
}
