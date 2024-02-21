package com.akash.e_learniverse_spring_boot.controller;

import com.akash.e_learniverse_spring_boot.response.ApiResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    private static final Logger logger = LogManager.getLogger(MainController.class);

    @GetMapping("main-home")
    public ResponseEntity<?> mainHome() {
        logger.info("E-Learniverse Home Api");
        return ResponseEntity.ok(new ApiResponseDto("E-Learniverse Home Api"));
    }

    @GetMapping("print-different-level-log")
    public ResponseEntity<?> printDifferentLevelLog() {
        logger.trace("Trace-Level");
        logger.debug("Debug-Level");
        logger.info("Info-Level");  //By Default Spring Boot ee Info-Level theke print hui log
        logger.warn("Warn-Level");
        logger.error("Error-Level");
        return ResponseEntity.ok(new ApiResponseDto("Log Printed"));
    }
}