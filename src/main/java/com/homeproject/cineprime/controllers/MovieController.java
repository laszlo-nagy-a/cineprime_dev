package com.homeproject.cineprime.controllers;

import com.homeproject.cineprime.models.Genre;
import com.homeproject.cineprime.models.Movie;
import com.homeproject.cineprime.services.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/{movie-id}")
    public Optional<Movie> findMovieById(@PathVariable("movie-id")Long id) {
        return movieService.getMovieById(id);
    }
}
