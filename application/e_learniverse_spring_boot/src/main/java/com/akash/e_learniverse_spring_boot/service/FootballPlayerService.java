package com.akash.e_learniverse_spring_boot.service;

import com.akash.e_learniverse_spring_boot.entity.FootballPlayerEntity;

import java.util.List;

public interface FootballPlayerService {

    FootballPlayerEntity save_player(FootballPlayerEntity footballPlayer);

    List<FootballPlayerEntity> get_all_football_player();

    FootballPlayerEntity findByEmail(String email);
}
