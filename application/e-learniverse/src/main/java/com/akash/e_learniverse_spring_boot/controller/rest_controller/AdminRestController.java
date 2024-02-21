package com.akash.e_learniverse_spring_boot.controller.rest_controller;

import com.akash.e_learniverse_spring_boot.domain.entity.FootballPlayerEntity;
import com.akash.e_learniverse_spring_boot.service.football_player.FootballPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@RestController
@RequestMapping("admin/api/")
public class AdminRestController {
    private static final Logger logger = LogManager.getLogger(AdminRestController.class);

    private final FootballPlayerService footballPlayerService;


    @Autowired
    public AdminRestController(FootballPlayerService footballPlayerService) {
        this.footballPlayerService = footballPlayerService;
    }

    @GetMapping("player-all")
    public ResponseEntity<?> getAllPlayer() {
        List<FootballPlayerEntity> playerList = footballPlayerService.getAllFootballPlayer();

//        return ResponseEntity.ok(new ApiResponseDto("Player List: " + playerEntityList.toString()));
        return ResponseEntity.ok(playerList);
    }

    @GetMapping("player-info")
    public ResponseEntity<?> getPlayerInfo(@RequestParam String player) {
        FootballPlayerEntity footballPlayer = footballPlayerService.getFootballPlayerByName(player);

        if (footballPlayer != null) {
//            return ResponseEntity.ok(new ApiResponseDto("Player Info: " + footballPlayer.toString()));
            return ResponseEntity.ok(footballPlayer);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
