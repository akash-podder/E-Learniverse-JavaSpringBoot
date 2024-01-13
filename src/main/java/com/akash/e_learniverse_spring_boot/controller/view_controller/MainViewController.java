package com.akash.e_learniverse_spring_boot.controller.view_controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainViewController {
    @Controller
    public class BaseController {
        @GetMapping("/")
        public String home(Model model){
            return "layout/home";
        }
    }
}
