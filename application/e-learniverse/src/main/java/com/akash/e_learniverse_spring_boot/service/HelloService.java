package com.akash.e_learniverse_spring_boot.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String greet(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Hola, Guest!";
        }
        return "Hola, " + name + "!";
    }
}