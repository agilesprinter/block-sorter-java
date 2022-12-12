package com.jakm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GPController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
