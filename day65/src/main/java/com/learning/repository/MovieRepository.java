package com.learning.repository;

import com.learning.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByOrderByRankAsc();
    Optional<Movie> findByRank(Integer rank);
}
