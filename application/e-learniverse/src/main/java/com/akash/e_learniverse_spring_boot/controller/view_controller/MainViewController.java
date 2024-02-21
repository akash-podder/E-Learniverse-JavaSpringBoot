package com.akash.e_learniverse_spring_boot.controller.view_controller;

import com.akash.e_learniverse_spring_boot.domain.dto.FootballPlayerDto;
import com.akash.e_learniverse_spring_boot.domain.entity.FootballPlayerEntity;
import com.akash.e_learniverse_spring_boot.mapper.CustomObjectMapper;
import com.akash.e_learniverse_spring_boot.service.football_player.FootballPlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainViewController {
    private static final Logger logger = LogManager.getLogger(MainViewController.class);

    private final CustomObjectMapper<FootballPlayerEntity, FootballPlayerDto> footballPlayerMapper;

    private final FootballPlayerService footballPlayerService;

    @Autowired
    public MainViewController(CustomObjectMapper<FootballPlayerEntity, FootballPlayerDto> footballPlayerMapper, FootballPlayerService footballPlayerService) {
        this.footballPlayerMapper = footballPlayerMapper;
        this.footballPlayerService = footballPlayerService;
    }


    @GetMapping("/")
    public String home(Model model) {
        return "layout/index";
    }

    //by DEFAULT, there are Two "/login" Endpoint (GET & POST)... we will Override GET method only as we want the Functionality of the "POST" method to be By Default of Spring Boot
    @GetMapping("/login")
    public String login(Model model) {
        return "layout/login";
    }

    @GetMapping("/create-player")
    public String showCreatePlayerForm(Model model) {
        // Add an empty Player object to the model for Thymeleaf to bind the form to
        model.addAttribute("player", new FootballPlayerDto());
        return "layout/create_player";
    }

    @PostMapping("/create-player")
    public String createPlayer(@ModelAttribute("player") FootballPlayerDto footballPlayerDto) {
        // Logic to save the player to the database or perform any necessary actions
        FootballPlayerEntity playerEntity = footballPlayerMapper.mapFrom(footballPlayerDto);

        try{
            FootballPlayerEntity newSavedFootballPlayer = footballPlayerService.savePlayer(playerEntity);

            // Redirect to a success page or another appropriate page
            return "redirect:/login";
        }
        catch (Exception ex){
            // Redirect to a Error page
            return "redirect:/";
        }


    }
}
