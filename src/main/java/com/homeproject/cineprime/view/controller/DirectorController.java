package com.homeproject.cineprime.view.controller;

import com.homeproject.cineprime.logic.exceptionHandler.ControllerExceptionHandler;
import com.homeproject.cineprime.logic.service.DirectorService;
import com.homeproject.cineprime.view.request_json.DirectorRequestJson;
import com.homeproject.cineprime.view.response_json.DirectorResponseJson;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("directors")
public class DirectorController {

    private DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    // TODO: kereső megvalósítás: paraméterek: title - type (search, type, mennyitől, meddig) -> oldalak megvalósítása
    @GetMapping
    public List<DirectorResponseJson> findAllDirector(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Optional<Integer> pagenumber,
            @RequestParam(required = false) Optional<Integer> pagesize
    ) {
        return directorService.getAllDirectorResponseJson(type, search, pagenumber, pagesize);
    }
    @GetMapping("/{director-public-id}")
    public DirectorResponseJson findDirector(@PathVariable("director-public-id") String publicId) {
        return directorService.getDirectorResponseJsonByPublicId(publicId);
    }

    @PostMapping
    public DirectorResponseJson createDirector(@Valid @RequestBody DirectorRequestJson request) {
        return directorService.createDirector(request);
    }

    @PutMapping
    public DirectorResponseJson updateDirectorById(@Valid @RequestBody DirectorRequestJson request) {
        return directorService.updateDirector(request);
    }

    @DeleteMapping("/{director-id}")
    public String removeDriectorById(@PathVariable("director-id") String publicId) {
        return directorService.removeDirectorByPublidId(publicId);
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
