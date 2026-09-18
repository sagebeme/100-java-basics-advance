package com.learning.service;

import com.learning.model.Movie;
import com.learning.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getTop10Movies() {
        List<Movie> all = movieRepository.findAllByOrderByRankAsc();
        return all.size() > 10 ? all.subList(0, 10) : all;
    }

    /**
     * Adds a movie at the bottom of the current ranking.
     */
    public Movie addMovie(Movie movie) {
        int nextRank = movieRepository.findAllByOrderByRankAsc().size() + 1;
        movie.setRank(nextRank);
        return movieRepository.save(movie);
    }

    /**
     * Moves a movie to newRank, shifting every movie between its old and new position by one
     * to make room, the way reordering a ranked list actually works.
     */
    public void setRank(Long movieId, int newRank) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("No movie with id " + movieId));
        int oldRank = movie.getRank();
        if (oldRank == newRank) {
            return;
        }

        List<Movie> all = movieRepository.findAllByOrderByRankAsc();
        if (newRank < oldRank) {
            // Moving up: everything from newRank..oldRank-1 shifts down by one.
            for (Movie m : all) {
                if (!m.getId().equals(movieId) && m.getRank() >= newRank && m.getRank() < oldRank) {
                    m.setRank(m.getRank() + 1);
                }
            }
        } else {
            // Moving down: everything from oldRank+1..newRank shifts up by one.
            for (Movie m : all) {
                if (!m.getId().equals(movieId) && m.getRank() > oldRank && m.getRank() <= newRank) {
                    m.setRank(m.getRank() - 1);
                }
            }
        }
        movie.setRank(newRank);
        movieRepository.saveAll(all);
        movieRepository.save(movie);
    }
}
