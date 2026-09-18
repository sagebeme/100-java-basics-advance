package com.learning.service;

import com.learning.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    private Movie add(String title) {
        return movieService.addMovie(new Movie(title, "Some Director", 2020, 8));
    }

    @Test
    void addingAMovieRanksItAtTheBottom() {
        add("First");
        Movie second = add("Second");
        assertEquals(2, second.getRank());
    }

    @Test
    void top10OnlyReturnsUpToTenMoviesEvenWithMore() {
        for (int i = 0; i < 12; i++) {
            add("Movie " + i);
        }
        assertEquals(10, movieService.getTop10Movies().size());
    }

    @Test
    void movingAMovieUpShiftsTheOnesBetweenDownByOne() {
        Movie a = add("A"); // rank 1
        Movie b = add("B"); // rank 2
        Movie c = add("C"); // rank 3

        movieService.setRank(c.getId(), 1);

        List<Movie> ranked = movieService.getTop10Movies();
        assertEquals("C", ranked.get(0).getTitle());
        assertEquals("A", ranked.get(1).getTitle());
        assertEquals("B", ranked.get(2).getTitle());
    }

    @Test
    void movingAMovieDownShiftsTheOnesBetweenUpByOne() {
        Movie a = add("A"); // rank 1
        Movie b = add("B"); // rank 2
        Movie c = add("C"); // rank 3

        movieService.setRank(a.getId(), 3);

        List<Movie> ranked = movieService.getTop10Movies();
        assertEquals("B", ranked.get(0).getTitle());
        assertEquals("C", ranked.get(1).getTitle());
        assertEquals("A", ranked.get(2).getTitle());
    }

    @Test
    void settingAMovieToItsOwnRankChangesNothing() {
        Movie a = add("A");
        add("B");

        movieService.setRank(a.getId(), 1);

        assertEquals("A", movieService.getTop10Movies().get(0).getTitle());
    }
}
