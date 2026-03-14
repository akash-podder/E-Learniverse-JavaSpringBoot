package com.akash.e_learniverse_spring_boot.service.football_club;

import com.akash.e_learniverse_spring_boot.domain.dto.FootballClubDto;

import java.util.List;

public interface FootballClubService {

    FootballClubDto createFootballClub(FootballClubDto footballClubDto);

    FootballClubDto getFootballClubByName(String clubName);

    List<FootballClubDto> getAllFootballClub();
}
