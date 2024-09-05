package com.homeproject.cineprime.view.controller;

import com.homeproject.cineprime.domain.model.Genre;
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

    // TODO: public_id-re kell a kéréseket visszaadni
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

    // TODO: public id-vel törlés
    @DeleteMapping("/{genre-id}")
    public String removeGenreById(@PathVariable("genre-id")Long id) {
        return genreService.deleteGenre(id);
    }

    // TODO: public id-vel update
    @PutMapping
    public GenreResponseJson updateGenre(@Valid @RequestBody GenreRequestJson request) {
        return genreService.updateGenre(request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        return ControllerExceptionHandler.handleValidationExceptions(exception);
    }
}
