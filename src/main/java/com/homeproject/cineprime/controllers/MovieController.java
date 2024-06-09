package com.homeproject.cineprime.controllers;

import com.homeproject.cineprime.dtos.MovieCardViewDto;
import com.homeproject.cineprime.dtos.MovieDetailViewDto;
import com.homeproject.cineprime.models.Movie;
import com.homeproject.cineprime.services.ControllerExceptionHandler;
import com.homeproject.cineprime.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<MovieCardViewDto> findAllMovieCard() {
        return movieService.getAllMovie();
    }

    @PostMapping("/movies")
    public Movie createMovie(@Valid @RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @PutMapping("/movies")
    public Movie updateMovie(@Valid @RequestBody Movie movieToUpdate) {
        return movieService.updateMovie(movieToUpdate);
    }

    @DeleteMapping("/movies/{movie-id}")
    public String removeMovieById(@PathVariable("movie-id")Long id) {
        return movieService.deleteMovie(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        return ControllerExceptionHandler.handleValidationExceptions(exception);
    }
}
