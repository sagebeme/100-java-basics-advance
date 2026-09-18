package com.learning;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This page shares its header and footer with every other page.");
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About");
        model.addAttribute("description", "Day 56 teaches Thymeleaf templates, variables and reusable layout fragments.");
        return "about";
    }
}
