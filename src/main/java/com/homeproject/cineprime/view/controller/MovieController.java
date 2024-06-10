package com.homeproject.cineprime.view.controller;

import com.homeproject.cineprime.logic.dto.MovieCardViewDto;
import com.homeproject.cineprime.logic.dto.MovieDetailViewDto;
import com.homeproject.cineprime.domain.model.Movie;
import com.homeproject.cineprime.logic.service.ControllerExceptionHandler;
import com.homeproject.cineprime.logic.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
    public void createMovie(@Valid @RequestBody Movie movie) {
        movieService.createMovie(movie);
    }

    @PutMapping("/movies")
    public void updateMovie(@Valid @RequestBody Movie movieToUpdate) {
        movieService.updateMovie(movieToUpdate);
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
