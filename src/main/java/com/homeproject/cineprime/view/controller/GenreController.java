package com.homeproject.cineprime.view.controller;

import com.homeproject.cineprime.logic.exceptionHandler.ControllerExceptionHandler;
import com.homeproject.cineprime.logic.service.GenreService;
import com.homeproject.cineprime.view.request_json.GenreRequestJson;
import com.homeproject.cineprime.view.response_json.GenreResponseJson;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("genres")
public class GenreController {
    private GenreService genreService;
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/{genre-id}")
    public GenreResponseJson findGenre(@PathVariable("genre-id")String publicId) {
        return genreService.getGenreResponseJsonById(publicId);
    }

    @GetMapping
    public List<GenreResponseJson> findAllGenre() {
        return genreService.getAllGenreResponseJson();
    }

    @PostMapping
    public GenreResponseJson createGenre(@Valid @RequestBody GenreRequestJson request) {
        return genreService.createGenre(request);
    }

    @PutMapping
    public GenreResponseJson updateGenre(@Valid @RequestBody GenreRequestJson request) {
        return genreService.updateGenre(request);
    }

    @DeleteMapping("/{genre-id}")
    public String removeGenreById(@PathVariable("genre-id")String publicId) {
        return genreService.removeGenreByPublidId(publicId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        return ControllerExceptionHandler.handleValidationExceptions(exception);
    }
}
