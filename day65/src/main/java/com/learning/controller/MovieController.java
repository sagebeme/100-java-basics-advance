package com.learning.controller;

import com.learning.model.Movie;
import com.learning.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.getTop10Movies());
        model.addAttribute("movie", new Movie());
        return "movies";
    }

    @PostMapping("/movies")
    public String addMovie(@ModelAttribute Movie movie) {
        movieService.addMovie(movie);
        return "redirect:/movies";
    }
}
