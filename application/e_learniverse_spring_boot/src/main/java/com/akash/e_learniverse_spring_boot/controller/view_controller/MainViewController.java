package com.akash.e_learniverse_spring_boot.controller.view_controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainViewController {
    private static final Logger logger = LogManager.getLogger(MainViewController.class);

    @GetMapping("/")
    public String home(Model model) {
        return "layout/index";
    }

    //by DEFAULT, there are Two "/login" Endpoint (GET & POST)... we will Override GET method only as we want the Functionality of the "POST" method to be By Default of Spring Boot
    @GetMapping("/login")
    public String login(Model model) {
        return "layout/login";
    }

    @GetMapping("/create/player")
    public String createPlayer(Model model) {
        return "student_layout/home";
    }
}
