package com.akash.e_learniverse_spring_boot.controller.rest_controller;

import com.akash.e_learniverse_spring_boot.domain.dto.FootballPlayerDto;
import com.akash.e_learniverse_spring_boot.domain.dto.request_dto.SendEmailRequestDto;
import com.akash.e_learniverse_spring_boot.pub_sub.publisher.EmailPublisher;
import com.akash.e_learniverse_spring_boot.pub_sub.publisher.EmailPublisherImpl;
import com.akash.e_learniverse_spring_boot.response.ApiResponseDto;
import com.akash.e_learniverse_spring_boot.response.PlayerAggregateResponse;
import com.akash.e_learniverse_spring_boot.service.football_player.FootballPlayerService;
import com.akash.e_learniverse_spring_boot.service.football_player.PlayerAggregationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/")
public class MainRestController {
    private static final Logger logger = LogManager.getLogger(MainRestController.class);

    private final FootballPlayerService footballPlayerService;
    private final PlayerAggregationService playerAggregationService;
    private final EmailPublisher emailPublisher;

    @Autowired
    public MainRestController(FootballPlayerService footballPlayerService, PlayerAggregationService playerAggregationService, EmailPublisherImpl emailPublisher) {
        this.footballPlayerService = footballPlayerService;
        this.playerAggregationService = playerAggregationService;
        this.emailPublisher = emailPublisher;
    }

    @GetMapping("home")
    public ResponseEntity<?> home() {
        logger.info("Home Api");
        return ResponseEntity.ok(new ApiResponseDto("E-Learniverse Home Api"));
    }


    @PostMapping("create-player")
    public ResponseEntity<?> createPlayer(@RequestBody FootballPlayerDto footballPlayerDto) {
        logger.info("Received: " + footballPlayerDto.toString());

        try{
            FootballPlayerDto newSavedFootballPlayer = footballPlayerService.savePlayer(footballPlayerDto);

            return ResponseEntity.ok(newSavedFootballPlayer);
        }
        catch (Exception ex){
            return ResponseEntity.ok(new ApiResponseDto(ex.toString()));
        }
    }


    @GetMapping("my-profile-info")
    public ResponseEntity<?> getMyPlayerInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //as during saving we used "Username = Email" of the FootballPlayer
        FootballPlayerDto footballPlayer = footballPlayerService.getFootballPlayerByEmail(authentication.getName());

        if (footballPlayer != null) {
            return ResponseEntity.ok(footballPlayer);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("send-email")
    public ResponseEntity<?> sendEmail(@RequestBody SendEmailRequestDto emailRequestDto) {
        logger.info("publishing to EmailQueue: {}", emailRequestDto);
        emailPublisher.publishEmailToQueue(emailRequestDto);

        return ResponseEntity.ok(new ApiResponseDto("Email Sent"));
    }

    //////////// Sync and Async DB Calls Comparison Endpoints ////////////

    // To Test Further with delay you can use "Thread.sleep()" with 2 seconds to simulate the Behavior
    @GetMapping("/players-aggregate/async-multithreading")
    public PlayerAggregateResponse getPlayersAsyncMultiThreading() throws Exception {
        // HTTP Request
        //      │
        //Controller
        //      │
        //Aggregation Service
        //      │
        //3 Async DB Calls (playerTaskExecutor)
        // ├── getBarcelonaPlayers()
        // ├── getMadridPlayers()
        // └── getLiverpoolPlayers()
        //      │
        //CompletableFuture.allOf()
        //      │
        //Combine results
        //      │
        //Return JSON
        return playerAggregationService.fetchPlayersAsyncMultiThreading();
    }

    // This API is just implemented to see Performance Comparison with "AsyncMultiThreading"
    @GetMapping("/players-aggregate/sync")
    public PlayerAggregateResponse getPlayersSync() throws Exception {
        return playerAggregationService.fetchPlayersSync();
    }
}