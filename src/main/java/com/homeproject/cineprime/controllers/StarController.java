package com.homeproject.cineprime.controllers;

import com.homeproject.cineprime.models.Genre;
import com.homeproject.cineprime.models.Star;
import com.homeproject.cineprime.services.ControllerExceptionHandler;
import com.homeproject.cineprime.services.StarService;
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
public class StarController {

    private StarService starService;

    public StarController(StarService starService) {
        this.starService = starService;
    }

    @PostMapping("/stars")
    public Star createGenre(@Valid @RequestBody Star star) {
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
    public Star updateGenre(@Valid @RequestBody Star starToUpdate) {
        return starService.updateGenre(starToUpdate);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        return ControllerExceptionHandler.handleValidationExceptions(exception);
    }
}
