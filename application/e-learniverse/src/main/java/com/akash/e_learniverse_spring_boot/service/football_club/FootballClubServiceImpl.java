package com.akash.e_learniverse_spring_boot.service.football_club;

import com.akash.e_learniverse_spring_boot.domain.dto.FootballClubDto;
import com.akash.e_learniverse_spring_boot.domain.entity.FootballClubEntity;
import com.akash.e_learniverse_spring_boot.mapper.CustomObjectMapper;
import com.akash.e_learniverse_spring_boot.repository.FootballClubRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FootballClubServiceImpl implements FootballClubService {
    private static final Logger logger = LogManager.getLogger(FootballClubServiceImpl.class);

    private final FootballClubRepository footballClubRepository;
    private final CustomObjectMapper<FootballClubEntity, FootballClubDto> footballClubMapper;

    @Autowired
    public FootballClubServiceImpl(FootballClubRepository footballClubRepository, CustomObjectMapper<FootballClubEntity, FootballClubDto> footballClubMapper) {
        this.footballClubRepository = footballClubRepository;
        this.footballClubMapper = footballClubMapper;
    }

    @Override
    public FootballClubDto createFootballClub(FootballClubDto footballClubDto) {
        FootballClubEntity clubEntity = footballClubMapper.mapFrom(footballClubDto);
        FootballClubEntity savedClub = footballClubRepository.save(clubEntity);
        return footballClubMapper.mapTo(savedClub);
    }

    @Override
    public FootballClubDto getFootballClubByName(String clubName) {
        FootballClubEntity clubEntity = footballClubRepository.findByName(clubName);
        return clubEntity != null ? footballClubMapper.mapTo(clubEntity) : null;
    }

    @Override
    public List<FootballClubDto> getAllFootballClub() {
        return footballClubRepository.findAll().stream()
                .map(footballClubMapper::mapTo)
                .collect(Collectors.toList());
    }
}
