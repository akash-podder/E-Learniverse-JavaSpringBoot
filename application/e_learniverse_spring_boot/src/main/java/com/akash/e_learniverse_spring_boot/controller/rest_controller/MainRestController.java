package com.akash.e_learniverse_spring_boot.controller.rest_controller;

import com.akash.e_learniverse_spring_boot.entity.FootballPlayerEntity;
import com.akash.e_learniverse_spring_boot.response.ApiResponseDto;
import com.akash.e_learniverse_spring_boot.service.FootballPlayerService;
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

    @Autowired
    public MainRestController(FootballPlayerService footballPlayerService) {
        this.footballPlayerService = footballPlayerService;
    }

    @GetMapping("home")
    public ResponseEntity<?> home() {
        logger.info("Home Api");
        return ResponseEntity.ok(new ApiResponseDto("E-Learniverse Home Api"));
    }


    @PostMapping("create-player")
    public ResponseEntity<?> createPlayer(@RequestBody FootballPlayerEntity footballPlayer) {
        logger.info("Received: " + footballPlayer.toString());

        FootballPlayerEntity newSavedFootballPlayer = footballPlayerService.savePlayer(footballPlayer);

        return ResponseEntity.ok(new ApiResponseDto("New Player Created: " + newSavedFootballPlayer.toString()));
    }

    @GetMapping("my-info")
    public ResponseEntity<?> getMyPlayerInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //as during saving we used "Username = Email" of the FootballPlayer
        FootballPlayerEntity footballPlayer = footballPlayerService.getFootballPlayerByEmail(authentication.getName());

        if (footballPlayer != null) {
            return ResponseEntity.ok(new ApiResponseDto("Player List: " + footballPlayer.toString()));
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}