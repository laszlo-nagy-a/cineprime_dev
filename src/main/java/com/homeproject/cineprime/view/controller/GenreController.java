package com.homeproject.cineprime.view.controller;

import com.homeproject.cineprime.logic.exceptionHandler.ControllerExceptionHandler;
import com.homeproject.cineprime.logic.service.GenreService;
import com.homeproject.cineprime.view.request_json.GenreRequestJson;
import com.homeproject.cineprime.view.response_json.GenreResponseJson;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("genres")
public class GenreController {
    private final GenreService genreService;
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/{genre-id}")
    public GenreResponseJson findGenre(@PathVariable("genre-id")String publicId) {
        return genreService.getGenreResponseJsonById(publicId);
    }

    @GetMapping
    public List<GenreResponseJson> findAllGenre(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, name = "pagesize") Optional<Integer> pageSize,
            @RequestParam(required = false, name = "pagenumber") Optional<Integer> pageNumber
    ) {
        return genreService.getAllGenreResponseJson(type, search, pageSize, pageNumber);
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
        return genreService.removeGenreByPublicId(publicId);
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
