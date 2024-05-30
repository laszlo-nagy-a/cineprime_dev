package com.homeproject.cineprime.controllers;

import com.homeproject.cineprime.models.Genre;
import com.homeproject.cineprime.services.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GenreController {
    private GenreService genreService;
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/genres")
    public Genre createGenre(@RequestBody Genre genre) {
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
}
