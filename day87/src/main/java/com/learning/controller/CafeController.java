package com.learning.controller;

import com.learning.model.Cafe;
import com.learning.model.PriceLevel;
import com.learning.service.CafeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CafeController {

    private final CafeService cafeService;

    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @GetMapping("/")
    public String search(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Boolean wifiOnly,
            @RequestParam(required = false) PriceLevel maxPrice,
            Model model) {

        List<Cafe> cafes = cafeService.search(location, minRating, wifiOnly, maxPrice);

        model.addAttribute("cafes", cafes);
        model.addAttribute("location", location);
        model.addAttribute("minRating", minRating);
        model.addAttribute("wifiOnly", wifiOnly != null && wifiOnly);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("priceLevels", PriceLevel.values());
        return "index";
    }
}
