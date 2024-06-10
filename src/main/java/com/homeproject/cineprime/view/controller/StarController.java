package com.homeproject.cineprime.view.controller;

import com.homeproject.cineprime.domain.model.Star;
import com.homeproject.cineprime.logic.service.ControllerExceptionHandler;
import com.homeproject.cineprime.logic.service.StarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
    public void createGenre(@Valid @RequestBody Star star) {
        starService.createStar(star);
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
    public void updateGenre(@Valid @RequestBody Star starToUpdate) {
        starService.updateGenre(starToUpdate);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        return ControllerExceptionHandler.handleValidationExceptions(exception);
    }
}
