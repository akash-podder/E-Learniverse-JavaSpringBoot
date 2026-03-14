package com.akash.e_learniverse_spring_boot.service.football_player;

import com.akash.e_learniverse_spring_boot.domain.dto.FootballPlayerDto;
import com.akash.e_learniverse_spring_boot.response.PlayerAggregateResponse;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PlayerAggregationService {

    private final PlayerAsyncQueryService playerAsyncQueryService;
    private final PlayerSyncQueryService playerSyncQueryService;

    public PlayerAggregationService(PlayerAsyncQueryService playerAsyncQueryService, PlayerSyncQueryService playerSyncQueryService) {
        this.playerAsyncQueryService = playerAsyncQueryService;
        this.playerSyncQueryService = playerSyncQueryService;
    }

    public PlayerAggregateResponse fetchPlayersAsyncMultiThreading() throws Exception {

        CompletableFuture<FootballPlayerDto> ramosPlayerFuture =
                playerAsyncQueryService.getRamosDetail();

        CompletableFuture<FootballPlayerDto> messiPlayerFuture =
                playerAsyncQueryService.getMessiDetail();

        CompletableFuture<FootballPlayerDto> kroosPlayerFuture =
                playerAsyncQueryService.getKroosDetail();

        // Wait for all the DB calls and that's why we say "allOf()" and then "join"
        CompletableFuture.allOf(
                ramosPlayerFuture,
                messiPlayerFuture,
                kroosPlayerFuture
        ).join();

        return new PlayerAggregateResponse(
                ramosPlayerFuture.get(),
                messiPlayerFuture.get(),
                kroosPlayerFuture.get()
        );
    }

    public PlayerAggregateResponse fetchPlayersSync() throws Exception {
        return new PlayerAggregateResponse(
                playerSyncQueryService.getRamosDetail(),
                playerSyncQueryService.getMessiDetail(),
                playerSyncQueryService.getKroosDetail()
        );
    }
}