package com.learning.controller;

import com.learning.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    private final ProjectService projectService;

    public PageController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("projects", projectService.listProjects());
        return "projects";
    }
}
