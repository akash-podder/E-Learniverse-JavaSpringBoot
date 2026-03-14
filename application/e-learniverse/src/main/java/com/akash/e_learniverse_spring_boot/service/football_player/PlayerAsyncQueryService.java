package com.akash.e_learniverse_spring_boot.service.football_player;

import com.akash.e_learniverse_spring_boot.domain.dto.FootballPlayerDto;
import com.akash.e_learniverse_spring_boot.domain.entity.FootballPlayerEntity;
import com.akash.e_learniverse_spring_boot.mapper.CustomObjectMapper;
import com.akash.e_learniverse_spring_boot.repository.FootballPlayerRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PlayerAsyncQueryService {

    private final FootballPlayerRepository footballPlayerRepository;
    private final CustomObjectMapper<FootballPlayerEntity, FootballPlayerDto> footballPlayerMapper;

    public PlayerAsyncQueryService(FootballPlayerRepository footballPlayerRepository, CustomObjectMapper<FootballPlayerEntity, FootballPlayerDto> footballPlayerMapper) {
        this.footballPlayerRepository = footballPlayerRepository;
        this.footballPlayerMapper = footballPlayerMapper;
    }

    @Async("playerTaskExecutor")
    public CompletableFuture<FootballPlayerDto> getRamosDetail() {
        FootballPlayerDto players = footballPlayerMapper.mapTo(footballPlayerRepository.findByEmail("ramos@gmail.com"));
        return CompletableFuture.completedFuture(players);
    }

    @Async("playerTaskExecutor")
    public CompletableFuture<FootballPlayerDto> getMessiDetail() {
        FootballPlayerDto players = footballPlayerMapper.mapTo(footballPlayerRepository.findByEmail("messi@gmail.com"));
        return CompletableFuture.completedFuture(players);
    }

    @Async("playerTaskExecutor")
    public CompletableFuture<FootballPlayerDto> getKroosDetail() {
        FootballPlayerDto players = footballPlayerMapper.mapTo(footballPlayerRepository.findByEmail("kroos@gmail.com"));
        return CompletableFuture.completedFuture(players);
    }
}