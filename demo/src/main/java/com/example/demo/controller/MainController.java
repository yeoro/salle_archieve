package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
    @GetMapping("/")
    public static String start() {
    	
        return "home";
    }

    @GetMapping("/home")
    public static String home() {

        return "home";
    }

}
