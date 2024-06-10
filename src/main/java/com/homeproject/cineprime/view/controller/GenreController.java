package com.homeproject.cineprime.view.controller;

import com.homeproject.cineprime.domain.model.Genre;
import com.homeproject.cineprime.logic.service.ControllerExceptionHandler;
import com.homeproject.cineprime.logic.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class GenreController {
    private GenreService genreService;
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/genres")
    public void createGenre(@Valid @RequestBody Genre genre) {
        genreService.createGenre(genre);
    }

    @GetMapping("/genres")
    public List<Genre> findAllGenre() {
        return genreService.getAllGenre();
    }

    @GetMapping("/genres/{genre-id}")
    public Optional<Genre> findGenreById(@PathVariable("genre-id")Long id) {
        return genreService.getGenreById(id);
    }

    @DeleteMapping("/genres/{genre-id}")
    public String removeGenreById(@PathVariable("genre-id")Long id) {
        return genreService.deleteGenre(id);
    }

    @PutMapping("/genres")
    public void updateGenre(@Valid @RequestBody Genre genreToUpdate) {
        genreService.updateGenre(genreToUpdate);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        return ControllerExceptionHandler.handleValidationExceptions(exception);
    }
}
