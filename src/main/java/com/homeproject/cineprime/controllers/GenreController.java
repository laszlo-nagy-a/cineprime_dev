package com.homeproject.cineprime.controllers;

import com.homeproject.cineprime.models.Genre;
import com.homeproject.cineprime.services.GenreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public Genre createGenre(@Valid @RequestBody Genre genre) {
        return genreService.createGenre(genre);
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
    public Genre updateGenre(@RequestBody Genre genreToUpdate) {
        return genreService.updateGenre(genreToUpdate);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
