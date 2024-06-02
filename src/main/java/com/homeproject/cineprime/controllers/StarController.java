package com.homeproject.cineprime.controllers;

import com.homeproject.cineprime.models.Genre;
import com.homeproject.cineprime.models.Star;
import com.homeproject.cineprime.services.StarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StarController {

    private StarService starService;

    public StarController(StarService starService) {
        this.starService = starService;
    }

    @PostMapping("/stars")
    public Star createGenre(@RequestBody Star star) {
        return starService.createStar(star);
    }

    @GetMapping("/stars")
    public List<Star> findAllStar() {
        return starService.getAllStar();
    }

    @GetMapping("/stars/{star-id}")
    public Optional<Star> findGenreById(@PathVariable("star-id")Long id) {
        return starService.getStarById(id);
    }

    @DeleteMapping("/stars/{star-id}")
    public String removeGenreById(@PathVariable("star-id")Long id) {
        return starService.deleteStar(id);
    }

    @PutMapping("/stars")
    public Star updateGenre(@RequestBody Star starToUpdate) {
        return starService.updateGenre(starToUpdate);
    }

}
