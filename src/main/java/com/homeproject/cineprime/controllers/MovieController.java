package com.homeproject.cineprime.controllers;

import com.homeproject.cineprime.dtos.MovieCardViewDto;
import com.homeproject.cineprime.dtos.MovieDetailViewDto;
import com.homeproject.cineprime.models.Movie;
import com.homeproject.cineprime.services.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/{movie-id}")
    public MovieDetailViewDto findMovieById(@PathVariable("movie-id")Long id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/movies")
    public List<MovieCardViewDto> finrAllMovieCard() {
        return movieService.getAllMovie();
    }

    @PostMapping("/movies")
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @PutMapping("/movies")
    public Movie updateMovie(@RequestBody Movie movieToUpdate) {
        return movieService.updateMovie(movieToUpdate);
    }

    @DeleteMapping("/movies/{movie-id}")
    public String removeMovieById(@PathVariable("movie-id")Long id) {
        return movieService.deleteMovie(id);
    }
}
