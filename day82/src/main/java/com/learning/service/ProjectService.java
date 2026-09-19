package com.learning.service;

import com.learning.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final List<Project> projects = List.of(
            new Project(
                    "House Price Predictor",
                    "A full-stack Spring Boot app that trains a multiple linear regression model "
                            + "from scratch (Gaussian elimination on the normal equations, no ML library) "
                            + "and serves predictions through both a REST API and a web form.",
                    List.of("Java", "Spring Boot", "Thymeleaf"),
                    "#"
            ),
            new Project(
                    "Blog Platform REST API",
                    "A full CRUD blog API with users, posts and authorship relationships, backed by "
                            + "Spring Data JPA.",
                    List.of("Java", "Spring Boot", "Spring Data JPA", "H2"),
                    "#"
            ),
            new Project(
                    "Text to Morse Code Converter",
                    "A bidirectional Morse code converter covering the full alphabet, digits and "
                            + "punctuation, with a console UI and clipboard support.",
                    List.of("Java"),
                    "#"
            )
    );

    public List<Project> listProjects() {
        return projects;
    }
}
