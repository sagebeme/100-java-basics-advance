package com.learning.controller;

import com.learning.exception.ApiException;
import com.learning.service.CryptoService;
import com.learning.service.IssService;
import com.learning.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class DashboardController {

    private final WeatherService weatherService;
    private final CryptoService cryptoService;
    private final IssService issService;

    public DashboardController(WeatherService weatherService, CryptoService cryptoService, IssService issService) {
        this.weatherService = weatherService;
        this.cryptoService = cryptoService;
        this.issService = issService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        // Each API is fetched independently and its own failure doesn't take down the other
        // sections - a real dashboard shouldn't go blank just because one of three APIs is down.
        try {
            model.addAttribute("weather", weatherService.fetchCurrentWeather());
        } catch (ApiException e) {
            model.addAttribute("weatherError", e.getMessage());
        }

        try {
            model.addAttribute("prices", cryptoService.fetchPrices(List.of("bitcoin", "ethereum")));
        } catch (ApiException e) {
            model.addAttribute("cryptoError", e.getMessage());
        }

        try {
            model.addAttribute("iss", issService.fetchCurrentLocation());
        } catch (ApiException e) {
            model.addAttribute("issError", e.getMessage());
        }

        model.addAttribute("lastUpdated", LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        return "dashboard";
    }
}
