package com.akash.e_learniverse_spring_boot.service.football_player;

import com.akash.e_learniverse_spring_boot.domain.dto.FootballPlayerDto;
import com.akash.e_learniverse_spring_boot.domain.entity.FootballPlayerEntity;
import com.akash.e_learniverse_spring_boot.mapper.CustomObjectMapper;
import com.akash.e_learniverse_spring_boot.repository.FootballPlayerRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PlayerSyncQueryService {

    private final FootballPlayerRepository footballPlayerRepository;
    private final CustomObjectMapper<FootballPlayerEntity, FootballPlayerDto> footballPlayerMapper;

    public PlayerSyncQueryService(FootballPlayerRepository footballPlayerRepository, CustomObjectMapper<FootballPlayerEntity, FootballPlayerDto> footballPlayerMapper) {
        this.footballPlayerRepository = footballPlayerRepository;
        this.footballPlayerMapper = footballPlayerMapper;
    }

    public FootballPlayerDto getRamosDetail() {
        return footballPlayerMapper.mapTo(footballPlayerRepository.findByEmail("ramos@gmail.com"));
    }

    public FootballPlayerDto getMessiDetail() {
        return footballPlayerMapper.mapTo(footballPlayerRepository.findByEmail("messi@gmail.com"));
    }

    public FootballPlayerDto getKroosDetail() {
        return footballPlayerMapper.mapTo(footballPlayerRepository.findByEmail("kroos@gmail.com"));
    }
}