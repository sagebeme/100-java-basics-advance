package com.learning.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String director;

    // "year" and "rank" are both reserved words in H2 (rank is a SQL window function), so both
    // need an explicit, different column name - same gotcha as "user" in Day 60.
    @Column(name = "release_year")
    private Integer year;
    private Integer rating;
    @Column(name = "movie_rank")
    private Integer rank;

    public Movie() {
    }

    public Movie(String title, String director, Integer year, Integer rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public Integer getRank() { return rank; }
    public void setRank(Integer rank) { this.rank = rank; }
}
