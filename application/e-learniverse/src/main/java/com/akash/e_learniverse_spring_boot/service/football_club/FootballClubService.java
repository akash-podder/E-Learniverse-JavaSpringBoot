package com.akash.e_learniverse_spring_boot.service.football_club;

import com.akash.e_learniverse_spring_boot.domain.entity.FootballClubEntity;
import com.akash.e_learniverse_spring_boot.domain.entity.FootballPlayerEntity;

import java.util.List;

public interface FootballClubService {

    FootballClubEntity createFootballClub(FootballClubEntity footballClub);

    FootballClubEntity getFootballClubByName(String clubName);

    List<FootballClubEntity> getAllFootballClub();
}
