package com.akash.e_learniverse_spring_boot.service.football_club;

import com.akash.e_learniverse_spring_boot.domain.entity.FootballClubEntity;
import com.akash.e_learniverse_spring_boot.domain.entity.FootballPlayerEntity;
import com.akash.e_learniverse_spring_boot.repository.FootballClubRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FootballClubServiceImpl implements FootballClubService {
    private static final Logger logger = LogManager.getLogger(FootballClubServiceImpl.class);

    private final FootballClubRepository footballClubRepository;

    @Autowired
    public FootballClubServiceImpl(FootballClubRepository footballClubRepository) {
        this.footballClubRepository = footballClubRepository;
    }

    @Override
    public FootballClubEntity createFootballClub(FootballClubEntity footballClub) {
        return footballClubRepository.save(footballClub);
    }

    @Override
    public FootballClubEntity getFootballClubByName(String clubName) {
        return footballClubRepository.findByName(clubName);
    }

    @Override
    public List<FootballClubEntity> getAllFootballClub() {
        return footballClubRepository.findAll();
    }
}
