package com.learning;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Pwani Day Trips");
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About us");
        return "about";
    }

    @GetMapping("/tours")
    public String tours(Model model) {
        model.addAttribute("title", "Day trips");
        model.addAttribute("tours", List.of(
                new Tour("Fort Jesus and Old Town walk", "Mombasa", 2500),
                new Tour("Wasini Island and dolphins", "Shimoni", 7800),
                new Tour("Haller Park at feeding time", "Bamburi", 1500)));
        return "tours";
    }
}
