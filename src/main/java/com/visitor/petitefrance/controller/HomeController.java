package com.visitor.petitefrance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home"; 
    }

    @GetMapping("/destinations")
    public String destinations() {
        return "destinations"; 
    }

    @GetMapping("/things-to-explore")
    public String thingsToExplore() {
        return "things-to-explore";
    }

    @GetMapping("/plan-your-trip")
    public String planYourTrip() {
        return "plan-your-trip"; 
    }

    @GetMapping("/calendar")
    public String calendar() {
        return "calendar"; 
    }

    
    @GetMapping("/map")
    public String map() {
        return "map"; 
    }

    @GetMapping("/language-selection")
    public String languageSelection() {
        return "language-selection"; 
    }

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }
}
