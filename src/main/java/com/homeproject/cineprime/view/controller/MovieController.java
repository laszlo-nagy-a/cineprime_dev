package com.homeproject.cineprime.view.controller;

import com.homeproject.cineprime.logic.exceptionHandler.ControllerExceptionHandler;
import com.homeproject.cineprime.logic.service.MovieService;
import com.homeproject.cineprime.view.response_json.MovieRequestJson;
import com.homeproject.cineprime.view.response_json.MovieResponseJson;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{movie-id}")
    public MovieResponseJson findMovieById(@PathVariable("movie-id")String publicId) {
        return movieService.getMovieByPublicId(publicId);
    }

    @GetMapping
    public List<MovieResponseJson> findAllMovie() {
        return movieService.getAllMovieResponseJson();
    }

    @PostMapping
    public MovieResponseJson createMovie(@Valid @RequestBody MovieRequestJson movieRequestJson) {
        return movieService.createMovie(movieRequestJson);
    }

    @PutMapping
    public MovieResponseJson updateMovie(@Valid @RequestBody MovieRequestJson movieRequestJson) {
        return movieService.updateMovie(movieRequestJson);
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResponseStatusException.class)
    public Map<String, String> entityNotFoundExceptionHandler(ResponseStatusException exception) {
        return ControllerExceptionHandler.handleNotFoundStatusExcetions(exception);
    }
}
