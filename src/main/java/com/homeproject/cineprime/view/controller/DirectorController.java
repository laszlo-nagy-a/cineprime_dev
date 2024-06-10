package com.homeproject.cineprime.view.controller;

import com.homeproject.cineprime.domain.model.Director;
import com.homeproject.cineprime.logic.service.ControllerExceptionHandler;
import com.homeproject.cineprime.logic.service.DirectorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class DirectorController {

    private DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }
    @GetMapping("/directors/{director-id}")
    public Optional<Director> findDirectorById(@PathVariable("director-id") Long id) {
        return directorService.getDirectorById(id);
    }

    @GetMapping("/directors")
    public List<Director> findAllDirector() {
        return directorService.getAllDirector();
    }

    //TODO: personData JSON postolás helyett megoldható valahogy?
    @PostMapping("/directors")
    public void createDirector(@Valid @RequestBody Director director) {
        directorService.createDirector(director);
    }

    @PutMapping("/directors")
    public void updateDirectorById(@Valid @RequestBody Director directorToUpdate) {
        directorService.updateDirector(directorToUpdate);
    }

    @DeleteMapping("/directors/{director-id}")
    public String removeDriectorById(@PathVariable("director-id") Long id) {
        return directorService.deleteDirector(id);
    }

    //TODO: hiba kezelés így jó lehet jakarta validationnal?
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        return ControllerExceptionHandler.handleValidationExceptions(exception);
    }
}
