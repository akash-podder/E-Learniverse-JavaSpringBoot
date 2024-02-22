package com.akash.e_learniverse_spring_boot.controller.view_controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/player")
public class LoggedInUserViewController {
    private static final Logger logger = LogManager.getLogger(LoggedInUserViewController.class);

    @GetMapping("/about")
    public String home(Model model) {
        return "layout/about";
    }

    //TODO hala madrid: Delete Page+Mechanism
    @GetMapping("/delete")
    public String deletePlayer(Model model) {
        return "layout/index";
    }
}
