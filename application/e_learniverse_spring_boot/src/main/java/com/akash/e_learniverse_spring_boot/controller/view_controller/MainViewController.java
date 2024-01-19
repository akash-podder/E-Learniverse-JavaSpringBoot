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
        return "index";
//        return "layout/home";
    }

    @GetMapping("/create-player")
    public String create_player(Model model) {
        return "layout/create_student";
    }

}
