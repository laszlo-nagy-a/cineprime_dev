package com.homeproject.cineprime.view.controller;

import com.homeproject.cineprime.logic.exceptionHandler.ControllerExceptionHandler;
import com.homeproject.cineprime.logic.service.DirectorService;
import com.homeproject.cineprime.view.request_json.DirectorRequestJson;
import com.homeproject.cineprime.view.response_json.DirectorResponseJson;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("directors")
public class DirectorController {

    private DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public List<DirectorResponseJson> findAllDirector() {
        return directorService.getAllDirectorResponseJson();
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

    // TODO: törlés logikailag a kapcsolódó entitások miatt?
    @DeleteMapping("/{director-id}")
    public String removeDriectorById(@PathVariable("director-id") String publicId) {
        return directorService.removeDirectorByPublidId(publicId);
    }

    //TODO: hiba kezelés így jó lehet jakarta validationnal?
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        return ControllerExceptionHandler.handleValidationExceptions(exception);
    }
}
