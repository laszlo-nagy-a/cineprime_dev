package com.homeproject.cineprime.controllers;

import com.homeproject.cineprime.models.Director;
import com.homeproject.cineprime.services.DirectorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public Director createDirector(@RequestBody Director director) {
        return directorService.createDirector(director);
    }

    @PutMapping("/directors")
    public Director updateDirectorById(@RequestBody Director directorToUpdate) {
        return directorService.updateDirector(directorToUpdate);
    }

    @DeleteMapping("/directors/{director-id}")
    public String removeDriectorById(@PathVariable("director-id") Long id) {
        return directorService.deleteDirector(id);
    }
}
