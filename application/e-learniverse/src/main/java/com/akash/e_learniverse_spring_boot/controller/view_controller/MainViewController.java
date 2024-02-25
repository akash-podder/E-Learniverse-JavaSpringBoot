package com.akash.e_learniverse_spring_boot.controller.view_controller;

import com.akash.e_learniverse_spring_boot.domain.dto.FootballClubDto;
import com.akash.e_learniverse_spring_boot.domain.dto.FootballPlayerDto;
import com.akash.e_learniverse_spring_boot.domain.dto.request_dto.SendEmailRequestDto;
import com.akash.e_learniverse_spring_boot.domain.entity.FootballClubEntity;
import com.akash.e_learniverse_spring_boot.domain.entity.FootballPlayerEntity;
import com.akash.e_learniverse_spring_boot.mapper.CustomObjectMapper;
import com.akash.e_learniverse_spring_boot.pub_sub.consumer.EmailPublisher;
import com.akash.e_learniverse_spring_boot.service.football_club.FootballClubService;
import com.akash.e_learniverse_spring_boot.service.football_player.FootballPlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainViewController {
    private static final Logger logger = LogManager.getLogger(MainViewController.class);

    private final CustomObjectMapper<FootballPlayerEntity, FootballPlayerDto> footballPlayerMapper;
    private final CustomObjectMapper<FootballClubEntity, FootballClubDto> footballClubMapper;
    private final FootballPlayerService footballPlayerService;
    private final FootballClubService footballClubService;
    private final EmailPublisher emailPublisher;


    @Autowired
    public MainViewController(CustomObjectMapper<FootballPlayerEntity, FootballPlayerDto> footballPlayerMapper, CustomObjectMapper<FootballClubEntity, FootballClubDto> footballClubMapper, FootballPlayerService footballPlayerService, FootballClubService footballClubService, EmailPublisher emailPublisher) {
        this.footballPlayerMapper = footballPlayerMapper;
        this.footballClubMapper = footballClubMapper;
        this.footballPlayerService = footballPlayerService;
        this.footballClubService = footballClubService;
        this.emailPublisher = emailPublisher;
    }


    @GetMapping("/")
    public String home(Model model) {
        List<FootballPlayerEntity> footballPlayerEntityList = footballPlayerService.getAllFootballPlayer();
        List<FootballClubEntity> footballClubEntityList = footballClubService.getAllFootballClub();

        model.addAttribute("football_player_all", footballPlayerEntityList);
        model.addAttribute("football_club_all", footballClubEntityList);
        model.addAttribute("email_request_obj", new SendEmailRequestDto());

        return "layout/index";
    }

    //by DEFAULT, there are Two "/login" Endpoint (GET & POST)... we will Override GET method only as we want the Functionality of the "POST" method to be By Default of Spring Boot
    @GetMapping("/login")
    public String login(Model model) {
        return "layout/login";
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        // Add an empty Player object to the model for Thymeleaf to bind the form to
        model.addAttribute("player", new FootballPlayerDto());
        return "layout/football_player/create_player";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("player") FootballPlayerDto footballPlayerDto) {
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


    @GetMapping("/create-player")
    public String showCreatePlayerForm(Model model) {
        // Add an empty Player object to the model for Thymeleaf to bind the form to
        model.addAttribute("player", new FootballPlayerDto());
        return "layout/football_player/create_player";
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

    @GetMapping("/create-club")
    public String showCreateClubForm(Model model) {
        // Add an empty Player object to the model for Thymeleaf to bind the form to
        model.addAttribute("football_club", new FootballClubDto());
        return "layout/football_club/create_football_club";
    }

    @PostMapping("/create-club")
    public String createClub(@ModelAttribute("football_club") FootballClubDto footballClubDto) {
        // Logic to save the player to the database or perform any necessary actions
        FootballClubEntity clubEntity = footballClubMapper.mapFrom(footballClubDto);

        try{
            FootballClubEntity newFootballClub = footballClubService.createFootballClub(clubEntity);

            // Redirect to a success page or another appropriate page
            return "redirect:/login";
        }
        catch (Exception ex){
            // Redirect to a Error page
            return "redirect:/";
        }
    }

    @PostMapping("/send-email")
    public String sendEmail(@ModelAttribute("email_request_obj") SendEmailRequestDto emailRequestDto) {
        logger.info("publishing to EmailQueue: {}", emailRequestDto);
        emailPublisher.publishEmailToQueue(emailRequestDto);

        return "redirect:/";
    }

    @PostMapping("/rating")
    public String showRating(Model model) {

        return "redirect:/";
    }
}
