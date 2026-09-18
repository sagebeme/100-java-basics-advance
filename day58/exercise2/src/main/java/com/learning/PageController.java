package com.learning;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("cards", List.of(
                new Card("Fast", "Spring Boot starts in seconds."),
                new Card("Simple", "Convention over configuration."),
                new Card("Tested", "Every page here has a real test.")
        ));
        return "index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}
