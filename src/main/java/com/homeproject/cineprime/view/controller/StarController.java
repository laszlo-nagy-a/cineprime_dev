package com.homeproject.cineprime.view.controller;

import com.homeproject.cineprime.logic.exceptionHandler.ControllerExceptionHandler;
import com.homeproject.cineprime.logic.service.StarService;
import com.homeproject.cineprime.view.request_json.StarRequestJson;
import com.homeproject.cineprime.view.response_json.StarResponseJson;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/stars")
public class StarController {
    private final StarService starService;

    public StarController(StarService starService) {
        this.starService = starService;
    }

    @GetMapping("/{star-id}")
    public StarResponseJson findStar(@PathVariable("star-id")String publicId) {
        return starService.getStarResponseJsonById(publicId);
    }

    @GetMapping
    public List<StarResponseJson> findAllStar(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, name = "pagesize") Optional<Integer> pageSize,
            @RequestParam(required = false, name = "pagenumber") Optional<Integer> pageNumber
    ) {
        return starService.getAllStarResponseJson(type, search, pageSize, pageNumber);
    }

    @PostMapping
    public StarResponseJson createStar(@Valid @RequestBody StarRequestJson request) {
        return starService.createStar(request);
    }

    @PutMapping
    public StarResponseJson updateStar(@Valid @RequestBody StarRequestJson request) {
        return starService.updateStar(request);
    }

    @DeleteMapping("/{star-id}")
    public String removeStarById(@PathVariable("star-id")String publicId) {
        return starService.removeStarByPublicId(publicId);
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
